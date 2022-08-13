pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'cd lecture-domain'
                sh './mvnw compile'
            }
        }
    }
}
