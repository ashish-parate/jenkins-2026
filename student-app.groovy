pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
          stage('pull') {
            steps {
                git 'https://github.com/javaparser/javaparser-maven-sample.git'
                // Add build steps here
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean package"
                // Add build steps here
            }
        }
        stage('Test') {
            steps {
              sh "mvn clean verify sonar:sonar -Dsonar.projectKey=student-app-1 -Dsonar.host.url=http://13.201.183.192:9000 -Dsonar.login=sqp_c1511939047002dd7dfbc30ea1726e6cdc94ed01"

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