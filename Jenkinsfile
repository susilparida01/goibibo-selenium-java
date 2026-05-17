pipeline {
    agent any

    environment {
        // Define the image name
        IMAGE_NAME = "goibibo-selenium-demo"
    }

    stages {
        stage('Checkout') {
            steps {
                // Pull code from the repository
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image from the Dockerfile in the root
                    docker.build("${IMAGE_NAME}:${env.BUILD_ID}", ".")
                }
            }
        }

        stage('Run Selenium Tests') {
            steps {
                script {
                    // On Windows, the .inside() method often fails because it tries to pass a Windows path (C:\...)
                    // as the container's working directory. Using a manual 'docker run' avoids this.
                    // We convert the Windows path to use forward slashes for Docker compatibility.
                    def workspacePath = env.WORKSPACE.replace('\\', '/')
                    bat "docker run --rm --shm-size=2g -v \"${workspacePath}:/app\" -w /app ${IMAGE_NAME}:${env.BUILD_ID} mvn test -Dheadless=true"
                }
            }
        }
    }

    post {
        always {
            // Publish TestNG/Surefire Results
            // Includes both TEST-*.xml (Surefire) and testng-results.xml (TestNG)
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true

            // Archive Extent Reports, Screenshots, and Page Sources for viewing in Jenkins
            archiveArtifacts artifacts: 'target/extent/*.html, target/screenshots/*', allowEmptyArchive: true
            
            // Note: If you have the 'HTML Publisher' plugin, you can also add:
            publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'target/extent', reportFiles: '*.html', reportName: 'Extent Report'])
        }
    }
}
