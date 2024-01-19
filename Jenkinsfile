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
                    cd starship
                    npx nx run core:test
                    echo ".......................Linting......................."
                    npx nx run core:lint
                '''
            }
        }
        stage('Deploy Dev') {
            steps {
                input 'Do you want to deploy to Dev?'
                parameters {
                    string(name: 'VERSION', defaultValue: '0.0.1', description: 'Provide the version number')
                }
                sh '''
                    echo ".......................Deploying Dev......................."
                    cd starship
                    npx nx run core:publish --ver=$VERSION
                '''
            }
        }

        stage('Deploy Staging') {
            steps {
                input 'Do you want to deploy to staging?'
                parameters {
                    string(name: 'VERSION', defaultValue: '0.0.1', description: 'Provide the version number')
                }
                sh '''
                    echo ".......................Deploying Staging......................."
                    cd starship
                    npx nx run core:publish --ver=$VERSION
                '''
            }
        }

        stage('Deploy Production') {
            steps {
                input 'Do you want to deploy to production?'
                parameters {
                    string(name: 'VERSION', defaultValue: '0.0.1', description: 'Provide the version number')
                }
                sh '''
                    echo ".......................Deploying Production......................."
                    cd starship
                    npx nx run core:publish --ver=$VERSION
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