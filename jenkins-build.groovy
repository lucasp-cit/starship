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
    try {
        sh '''
            echo ".......................Testing......................."
            cd starship
            npx nx run core:test
            echo ".......................Linting......................."
            npx nx run core:lint
        '''
    }
}

def publish() {
    echo 'Publishing done 😉'
}

return this