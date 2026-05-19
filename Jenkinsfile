pipeline {
	agent any

	environment {
		DOCKER_HUB_CREDENTIALS = 'docker-hub'
		DOCKER_IMAGE = 'o2h2o2004/teedy'
		DOCKER_TAG = "${env.BUILD_NUMBER}"
	}

	stages {
		stage('Build') {
			steps {
				checkout scmGit(
					branches: [[name: '*/master']],
					extensions: [],
					userRemoteConfigs: [[url: 'https://github.com/02H20/Teedy.git']]
				)
				sh 'mvn -B -DskipTests clean package'
			}
		}

		stage('Building image') {
			steps {
				script {
					docker.build("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}")
				}
			}
		}

		stage('Upload image') {
			steps {
				script {
					docker.withRegistry('https://registry.hub.docker.com', DOCKER_HUB_CREDENTIALS) {
						docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push()
						docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push('latest')
					}
				}
			}
		}

		stage('Run containers') {
			steps {
				script {
					// 启动 8082 容器
					sh 'docker stop teedy-container-8082 || true'
					sh 'docker rm teedy-container-8082 || true'
					docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy-container-8082 -p 8082:8080')

					// 启动 8083 容器
					sh 'docker stop teedy-container-8083 || true'
					sh 'docker rm teedy-container-8083 || true'
					docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy-container-8083 -p 8083:8080')

					// 启动 8084 容器
					sh 'docker stop teedy-container-8084 || true'
					sh 'docker rm teedy-container-8084 || true'
					docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy-container-8084 -p 8084:8080')

					// 打印出所有正在运行的 teedy 容器
					sh 'docker ps --filter "name=teedy-container"'
				}
			}
		}
	}
}
