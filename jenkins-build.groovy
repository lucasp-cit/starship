#!groovy

void build() {
    sh '''
        echo ".......................Building......................."
        cd starship
        npm install
        npx nx run core:build
    '''
}

void test() {
    sh '''
        echo ".......................Testing......................."
        cd starship
        npx nx run core:test
        echo ".......................Linting......................."
        npx nx run core:lint
    '''
}

def deploy(env) {
    echo ".......................Deploying to ${env}......................."
    // sh '''
    //     echo ".......................Deploying Dev......................."
    //     cd starship
    //     npx nx run core:publish --ver=$VERSION --userconfig=$ARTIFACTORY_CREDENTIALS
    // '''
}

def mergeAndCreateTagOnMain(releaseNumberFromBranch) {
    echo ".......................Merging and Creating tag ${releaseNumberFromBranch} on Main......................."
    checkout([
        $class: 'GitSCM',
        branches: [[name: 'main']],
        extensions: [
            [$class: 'CloneOption', noTags: true, reference: '', shallow: true]
        ],
        submoduleCfg: [],
        userRemoteConfigs: [
            [ credentialsId: 'lucasp-ciandt', url: 'https://github.com/lucasp-cit/starship.git']
        ]
    ])
    sh "git checkout main"
    sh "git merge --squash release/${releaseNumberFromBranch}"
    sh "git tag -a tag/${releaseNumberFromBranch} -m \"Release Tag for version: ${${releaseNumberFromBranch}}\""
    sh "git push origin tag/${releaseNumberFromBranch}"
}


return this