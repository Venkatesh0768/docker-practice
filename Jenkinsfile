@Library('Shared') _

pipeline {
    agent { 
        label 'dev'
    };

    stages {

        stage("Code Cloning") {
            steps {
               script{
                   clone("https://github.com/Venkatesh0768/docker-practice.git", "main")
               }
            }
        }
        stage("trivy security checks") {
            steps {
               script{
                   trivy_fs()
               }
            }
        }

        

        stage("Build Frontend Image") {
            steps {
                dir("frontend") {
                    // sh "docker build -t venkatesh0768/frontend-docker:latest -f docker-production ."
                    frontend_image(venkatesh0768 , frontend-docker ,docker-production)
                }
            }
        }

        stage("Build Backend Image") {
            steps {
                dir("backend-todo") {
                    // sh "docker build -t venkatesh0768/backend-docker:latest -f spring-boot-distroless ."
                    backend_image(venkatesh0768 ,backend-docker , spring-boot-distroless )
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
                // withCredentials([usernamePassword(
                //     credentialsId: "dockerHubCreds",
                //     usernameVariable: "dockerHubUser",
                //     passwordVariable: "dockerHubPass"
                // )]) {

                //     sh """
                //         echo "${env.dockerHubPass}" | docker login -u "${env.dockerHubUser}" --password-stdin
                //         docker push ${env.dockerHubUser}/frontend-docker:latest
                //         docker push ${env.dockerHubUser}/backend-docker:latest
                //         docker logout
                //     """
                // }

                script{
                    docker_push(dockerHubCreds ,frontend-docker ,backend-docker )
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
    
    post {
        failure{
            emailtext(
                to: "rapoluvenky8@gmail.com",
                subject: "Build Failed : ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Build ${env.BUILD_NUMBER} failed. Check Jenkins console output.",
            )
        }
        success{
            script{
                mail(
                    to: "rapoluvenky8@gmail.com",
                    subject: "Build Success : ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """
                        Job name : ${env.JOB_NAME}
                        Build Number : ${env.BUILD_NUMBER}
                    """
                )
            }
        }
    }
}
