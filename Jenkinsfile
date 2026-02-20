pipeline {
    agent { 
        label 'dev'
    };

    stages {

        stage("Code Cloning") {
            steps {
                git url: "https://github.com/Venkatesh0768/docker-practice.git", branch: "main"
            }
        }

        stage("Build Frontend Image") {
            steps {
                dir("frontend") {
                    sh "docker build -t venkatesh0768/frontend-docker:latest -f docker-production ."
                }
            }
        }

        stage("Build Backend Image") {
            steps {
                dir("backend-todo") {
                    sh "docker build -t venkatesh0768/backend-docker:latest -f spring-boot-distroless ."
                }
            }
        }

        stage("Testing") {
            steps {
                echo "Running test cases..."
            }
        }

        stage("Push Images to Docker Hub") {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "dockerHubCreds",
                    usernameVariable: "dockerHubUser",
                    passwordVariable: "dockerHubPass"
                )]) {

                    sh """
                        echo "${env.dockerHubPass}" | docker login -u "${env.dockerHubUser}" --password-stdin
                        docker push ${env.dockerHubUser}/frontend-docker:latest
                        docker push ${env.dockerHubUser}/backend-docker:latest
                        docker logout
                    """
                }
            }
        }

        stage("Deploy") {
            steps {
                sh """
                    docker compose down || true
                    docker compose pull
                    docker compose up -d --build --force-recreate
                """
            }
        }
    }
}
