pipeline {
    agent any
    tools {
        nodejs 'nodejs' // Check the name they set for this tool
    }
    environment {
        ARTIFACTORY_CREDENTIALS = credentials('ARTIFACTORY_CREDENTIALS')
    }
    stages {
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
        stage('Deploy Dev') {
            input {
                message "Do you want to deploy to dev?"
                ok "Yes"
                parameters {
                    string(name: 'VERSION', defaultValue: '0.0.1', description: 'Provide the version number')
                }
            }
            steps {
                writeFile file: 'starship/.npmrc', text: "$ARTIFACTORY_CREDENTIALS"
                sh '''
                    echo ".......................Deploying Dev......................."
                    cd starship
                    npx nx run core:publish --ver=$VERSION --userconfig=$ARTIFACTORY_CREDENTIALS
                '''
            }
        }

        stage('Deploy Staging') {
            input {
                message "Do you want to deploy to staging?"
                ok "Yes"
                parameters {
                    string(name: 'VERSION', defaultValue: '0.0.1', description: 'Provide the version number')
                }
            }
            steps {
                writeFile file: 'starship/.npmrc', text: "$ARTIFACTORY_CREDENTIALS"
                sh '''
                    echo ".......................Deploying Staging......................."
                    cd starship
                    npx nx run core:publish --ver=$VERSION --userconfig=$ARTIFACTORY_CREDENTIALS
                '''
            }
        }

        stage('Deploy Production') {
            input {
                message "Do you want to deploy to production?"
                ok "Yes"
                parameters {
                    string(name: 'VERSION', defaultValue: '0.0.1', description: 'Provide the version number')
                }
            }
            steps {
                writeFile file: 'starship/.npmrc', text: "$ARTIFACTORY_CREDENTIALS"
                sh '''
                    echo ".......................Deploying Production......................."
                    cd starship
                    npx nx run core:publish --ver=$VERSION --userconfig=$ARTIFACTORY_CREDENTIALS
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