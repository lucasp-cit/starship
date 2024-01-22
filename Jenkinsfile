#!groovy

node() {
    checkout scm

    def script = load('jenkins-build.groovy') as BuildScript
    def isMainBranch = releaseBranch || masterBranch

    try {
        stage('Build') {
            // nodejs(nodeJSInstallationName: 'nodejs') {
                script.build()
            // }
        }

        stage('Test') {
            // nodejs(nodeJSInstallationName: 'nodejs') {
                script.test()
            // }
        }

        currentBuild.result = 'SUCCESS'
    } catch (e) {
        currentBuild.result = 'FAILURE'
        throw e
    } finally {
        if (isBuildBroken) {
            echo "ERROR: ${committer} just broke the build!"
        }

        if (isBuildFixed) {
            echo "INFO: ${committer} just repaired the build. Well done."
        }
    }

    if (isMainBranch) {
        def artifact = isMasterBranch() ? 'Snapshot' : 'Release'

        if (releaseBranch) {
            timeout(time: 30, unit: 'MINUTES') {
                input "Please confirm release ${releaseNumberFromBranch}"
            }
        }

        stage("Publish $artifact") {
            script.publish()
        }
    }
}

interface BuildScript {
    void build()
    void test()
    void publish()
}

String getCommitter() {
    sh(returnStdout: true, script: "git log -n1 --pretty='%an'").trim()
}

boolean isMasterBranch() {
    ['master', 'main'].contains(BRANCH_NAME)
}

boolean isDevelopBranch() {
    ['develop', 'development', 'dev'].contains(BRANCH_NAME)
}

boolean isReleaseBranch() {
    BRANCH_NAME.startsWith('release')
}

String getReleaseNumberFromBranch() {
    def matcher = BRANCH_NAME =~ /release\/(.+)$/

    if (!matcher.matches()) {
        error("Release specifier not found in branchname: ${BRANCH_NAME}")
    }

    matcher.group(1)
}

void abort(String message) {
    currentBuild.result = 'ABORTED'
    throw new hudson.AbortException(message)
}

String getLastBuildStatus() {
    currentBuild.previousBuiltBuild?.result
}

boolean getIsBuildFixed() {
    currentBuild.result == 'SUCCESS' && lastBuildStatus == 'FAILURE'
}

boolean getIsBuildBroken() {
    currentBuild.result == 'FAILURE' && lastBuildStatus == 'SUCCESS'
}