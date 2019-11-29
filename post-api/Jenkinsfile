pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /tmp/.m2:/tmp/.m2'
        }
    }
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -Dspring.profiles.active=test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Coverage') {
            steps {
                sh 'mvn clean package jacoco:report'
            }
            post {
                always {
                    publishCoverage adapters: [jacocoAdapter('target/site/jacoco/jacoco.xml')]
                }
            }
        }
    }
}