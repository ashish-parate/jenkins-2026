pipeline {
    agent any
    stages {
        stage('pull') {
            steps {
                echo 'pull succesed'
                
            }
        }
    
        stage('build') {
            steps {
                echo 'build is done'
                
            }
            
        }
        stage('test'){
            steps {
                echo 'test is done'
                
            }
            
        }
        stage('deploy'){
            steps {
                echo 'success deploy'
                
            }
            
        }
        
    }
}