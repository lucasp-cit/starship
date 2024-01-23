#!groovy

node() {
    checkout scm

    def script = load('jenkins-build.groovy') as BuildScript

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
            input {
                message "Do you want to deploy"
                ok "Yes"
                parameters {
                    string(name: 'VERSION', defaultValue: releaseNumberFromBranch, description: 'Provide the version number:')
                }
            }
            
            nodejs('nodejs') {
                script.deploy('staging')
            }
        }

        stage("Deploy to Production") {
            input {
                message "Do you want to deploy"
                ok "Yes"
                parameters {
                    string(name: 'VERSION', defaultValue: releaseNumberFromBranch, description: 'Provide the version number:')
                }
            }
            nodejs('nodejs') {
                script.deploy('prod')
            }
        }
    }

    // TO-DO: create
    if (masterBranch) {
       
    }
    
}

interface BuildScript {
    void build()
    void test()
    void deploy(String env)
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