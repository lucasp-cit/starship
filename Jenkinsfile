pipeline {
    agent any
    tools {
        nodejs 'nodejs' // Check the name they set for this tool
    }
    stages {
        stage('Build') {
            steps {
                sh '''
                    echo ".......................Building......................."
                    cd starship
                    npm install
                    npx nx run core:build
                '''
            }
        }
        stage('Test') {
            steps {
                sh '''
                    echo ".......................Testing......................."
                    npx nx run core:test
                    echo ".......................Linting......................."
                    npx nx run core:lint
                '''
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                    echo ".......................Deploying......................."
                    npx nx run core:publish
                '''
            }
        }
    }
    post {
        always {
            echo 'I will always get executed :D'
        }
        success {
            echo 'I will only get executed if this success'
        }
        failure {
            echo 'I will only get executed if this fails'
        }
        unstable {
            echo 'I will only get executed if this is unstable'
        }
    }
}