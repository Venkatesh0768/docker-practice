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
                    frontend_image("venkatesh0768" , "frontend-docker" ,"docker-production")
                }
            }
        }

        stage("Build Backend Image") {
            steps {
                dir("backend-todo") {
                    backend_image("venkatesh0768" ,"backend-docker" , "spring-boot-distroless" )
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
                script{
                    docker_push("dockerHubCreds" ,"frontend-docker" ,"backend-docker" )
                }
            }
        }

        stage("Deploy") {
            steps {
                script{
                    docker_deploy()
                }
            }
        }
    }
    
    post {
        failure{
            script {
                emailext(
                    subject: "${currentBuild.currentResult}: ${env.JOB_NAME} ${env.BUILD_NUMBER}",
                    to: "rapoluvenky7@example.com",
                    body: "${env.BUILD_URL}"
                    )
                }
            }
        success{
            script {
                emailext(
                    subject: "${currentBuild.currentResult}: ${env.JOB_NAME} ${env.BUILD_NUMBER}",
                    to: "rapoluvenky7@example.com",
                    body: "${env.BUILD_URL}"
                    )
                }
            }
        }
    }
}
