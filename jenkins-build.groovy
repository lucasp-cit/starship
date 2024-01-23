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
    
    sh '''
        echo ".......................Merging and Creating tag $releaseNumberFromBranch on Main......................."
    '''
        //     git checkout main
        // git merge --squash release/$releaseNumberFromBranch
        // git tag -a tagName -m \"Tag message\"
        // git push origin tagName
}


return this