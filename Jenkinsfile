#!groovy

node() {
    checkout scm

    def script = load('jenkins-build.groovy')

    try {
        stage('Build') {
            nodejs('nodejs') {
                script.build()
            }
        }

        stage('Test') {
            nodejs('nodejs') {
                script.test()
            }
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

    if(developBranch){
        stage("Deploy to Development") {
            nodejs('nodejs') {
                script.deploy('dev')
            }
        }
    }

    if (releaseBranch) {
        stage("Deploy to Staging") {
            input(
                message: "Do you want to deploy",
                ok: "Yes",
                parameters: [
                    string(name: 'VERSION', defaultValue: releaseNumberFromBranch, description: 'Provide the version number:')
                ]
            )
            
            nodejs('nodejs') {
                script.deploy('staging')
            }
        }

        stage("Merge on Main") {
            input( 
                message: "Do you want to merge and create Tag on Main?",
                ok: "Yes"
            )
            nodejs('nodejs') {
                script.mergeAndCreateTagOnMain(releaseNumberFromBranch)
            }
        }
    }

    if (masterBranch) {
        input( 
            message: "Should we deploy to Production?",
            ok: "Yes"
        )
        stage("Deploy to Production") {
            nodejs('nodejs') {
                script.deploy('prod')
            }
        }
    }
    
}

String getCommitter() {
    sh(returnStdout: true, script: "git log -n1 --pretty='%an'").trim()
}

boolean isReleaseBranch() {
    BRANCH_NAME.startsWith('release')
}

boolean isDevelopBranch() {
    ['develop', 'development', 'dev'].contains(BRANCH_NAME)
}

boolean isMasterBranch() {
    ['master', 'main'].contains(BRANCH_NAME)
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