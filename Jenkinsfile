pipeline {
    agent any

    environment {
        APP_NAME = "electronic-store-app"
        REMOTE_USER = "ubuntu" // or your server user
        REMOTE_HOST = "your-server-ip"
        DEPLOY_DIR = "/home/ubuntu/apps"
    }

    stages {
        stage('Clone Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Rishisingh96/Electronic-Store-App.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy to Server') {
            steps {
                sh """
                    scp target/*.jar ${REMOTE_USER}@${REMOTE_HOST}:${DEPLOY_DIR}/${APP_NAME}.jar
                """
            }
        }

        stage('Run Spring Boot App') {
            steps {
                sh """
                    ssh ${REMOTE_USER}@${REMOTE_HOST} '
                        pkill -f ${APP_NAME}.jar || true
                        nohup java -jar ${DEPLOY_DIR}/${APP_NAME}.jar > ${DEPLOY_DIR}/app.log 2>&1 &
                    '
                """
            }
        }
    }

    post {
        success {
            echo "✅ Deployment successful!"
        }
        failure {
            echo "❌ Deployment failed!"
        }
    }
}
