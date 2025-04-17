pipeline {
    agent any

    environment {
        JAR_NAME = "electronic-store-app-0.0.1-SNAPSHOT.jar"
        APP_PORT = "8081"
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/Rishisingh96/Electronic-Store-App.git'
            }
        }

        stage('Build App') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Stop Previous App') {
            steps {
                sh '''
                    PID=$(lsof -ti :${APP_PORT}) || true
                    if [ ! -z "$PID" ]; then
                      kill -9 $PID
                      echo "Stopped running app on port ${APP_PORT}"
                    fi
                '''
            }
        }

        stage('Deploy App') {
            steps {
                sh '''
                    nohup java -Dserver.port=${APP_PORT} -jar target/${JAR_NAME} > app.log 2>&1 &
                    echo "App started on port ${APP_PORT}"
                '''
            }
        }
    }

    post {
        success {
            echo "✅ Deployment Successful! Visit: http://3.110.165.13:${APP_PORT}/"
        }
        failure {
            echo "❌ Deployment Failed"
        }
    }
}
