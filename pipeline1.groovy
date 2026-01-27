pipeline {
    agent any

    stages {
          stage('pull') {
            steps {
                git 'https://github.com/shubhamkalsait/studentapp-ui.git'
                // Add build steps here
            }
        }
        stage('Build') {
            steps {
                echo 'Building successfully...'
                // Add build steps here
            }
        }
        stage('Test') {
            steps {
                echo 'Testing successfully...'
                // Add test steps here
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying successfully...'
                // Add deploy steps here
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}