pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh '''
                    echo ".......................Building......................."
                    pwd
                    ls -lah

                    cd starship

                    npm install

                    docker --version
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