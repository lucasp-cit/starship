pipeline {
    agent any
    tools {
        nodejs 'NodeJS 21.6.0'
    }
    stages {
        stage('Build') {
            steps {
                sh '''
                    echo ".......................Building......................."
                    pwd
                    ls -lah

                    cd starship

                    npm install
                '''
            }
        }
        stage('Test') {
            steps {
                sh '''
                    echo ".......................Testing......................."
                    pwd
                    ls -lah
                '''
            }
        }
        stage('Deploy') {
            steps {
                echo '.......................Deploying.......................'
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