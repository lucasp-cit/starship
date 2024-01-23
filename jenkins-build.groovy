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

def mergeOnMain() {
    echo ".......................Merging on Main......................."
}

def createTagOnMain() {
    echo ".......................Creating tag on Main......................."
}

return this