pipeline {
    agent any
    tools {
        maven 'Maven 3.3.9'
        jdk 'jdk11'
    }    
    stages {
        stage('Build') {
            steps {
                sh 'cd lecture-domain'
                sh 'mvn compile'
            }
        }
    }
}
