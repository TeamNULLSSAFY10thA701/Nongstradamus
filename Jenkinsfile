pipeline {
    agent any

    stages {
 
        stage('FE 빌드 및 빌드파일 복사') {
            
            when {
                expression { env.GIT_BRANCH == 'origin/dev-fe' || env.GIT_BRANCH == 'origin/dev' || env.GIT_BRANCH == 'origin/master' }
            }
            
            steps {
                dir('Frontend') {
                    script {
                        try {
                            sh "docker rm -f \$(docker ps -aqf \"name=^/frontend\$\")"
                        } catch (Exception e) {
                            echo "컨테이너 제거 중 에러가 발생했습니다: ${e.getMessage()}"
                        }
                    }
                    withCredentials([file(credentialsId: 'FE-Environment', variable: 'env')]) {
                        sh 'cp $env ./.env'
                    }      
                    sh "docker build -t frontend ."
                    sh "docker run --name frontend frontend"
                    sh "docker cp frontend:/app/dist /data"
                    sh "docker exec nginx nginx -s reload"
                }
            }

            post {
                success {
                    updateGitlabCommitStatus name: 'build', state: 'success'
                    echo "프론트엔드 빌드 및 복사 성공"
                }
                
                failure {
                    updateGitlabCommitStatus name: 'build', state: 'failed'
                    echo "프론트엔드 빌드 및 복사 실패"
                }
            }
        }

        stage('BE 빌드') {
            
            when {
                expression { env.GIT_BRANCH == 'origin/dev-be' || env.GIT_BRANCH == 'origin/dev' || env.GIT_BRANCH == 'origin/master' }
            }
            
            steps {
                dir('Backend') {
                    withCredentials([file(credentialsId: 'BE-Environment', variable: 'applicationyml')]) {
                        sh 'mkdir -p src/main/resources'
                        sh 'cp $applicationyml src/main/resources/application.yml'
                    }
                    sh "docker build -t backend ."                   
                }
            }

            post {
                success {
                    echo "백엔드 빌드 성공"
                }
                
                failure {
                    echo "백엔드 빌드 실패"
                }
            }

        }

        stage('BE 도커 컨테이너 실행') {
            
            when {
                expression { env.GIT_BRANCH == 'origin/dev-be' || env.GIT_BRANCH == 'origin/dev' || env.GIT_BRANCH == 'origin/master' }
            }
            
            steps {    
                script {
                    try {
                        sh "docker rm -f \$(docker ps -aqf \"name=^/backend\$\")"
                    } catch (Exception e) {
                        echo "컨테이너 제거 중 에러가 발생했습니다: ${e.getMessage()}"
                    }
                }         
                    sh "docker run -d -p 8080:8080 -e TZ=Asia/Seoul --name backend backend"
            }

            post {
                success {
                    updateGitlabCommitStatus name: 'build', state: 'success'
                    echo "백엔드 컨테이너화 성공"
                }
                
                failure {
                    updateGitlabCommitStatus name: 'build', state: 'failed'
                    echo "백엔드 컨테이너화 실패"
                }
            }

        }

        stage('Flask 빌드') {
            
            when {
                expression { env.GIT_BRANCH == 'origin/dev-data' || env.GIT_BRANCH == 'origin/dev' || env.GIT_BRANCH == 'origin/master' }
            }
            
            steps {
                dir('Data') {
                    withCredentials([file(credentialsId: 'Flask-Environment', variable: 'configpy')]) {
                        sh 'cp $configpy ./config.py'
                    }
                    sh "docker build -t flask ."                   
                }
            }

            post {
                success {
                    echo "Flask 빌드 성공"
                }
                
                failure {
                    echo "Flask 빌드 실패"
                }
            }

        }

        stage('Flask 도커 컨테이너 실행') {
            
            when {
                expression { env.GIT_BRANCH == 'origin/dev-data' || env.GIT_BRANCH == 'origin/dev' || env.GIT_BRANCH == 'origin/master' }
            }
            
            steps {    
                script {
                    try {
                        sh "docker rm -f \$(docker ps -aqf \"name=^/flask\$\")"
                    } catch (Exception e) {
                        echo "컨테이너 제거 중 에러가 발생했습니다: ${e.getMessage()}"
                    }
                }         
                    sh "docker run -d -p 5050:5050 -e TZ=Asia/Seoul --name flask flask"
            }

            post {
                success {
                    updateGitlabCommitStatus name: 'build', state: 'success'
                    echo "Flask 컨테이너화 성공"
                }
                
                failure {
                    updateGitlabCommitStatus name: 'build', state: 'failed'
                    echo "Flask 컨테이너화 실패"
                }
            }

        }
        
    }

}
