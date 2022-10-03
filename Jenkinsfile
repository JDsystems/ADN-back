@Library('ceiba-jenkins-library') _
pipeline {
  //Donde se va a ejecutar el Pipeline
  agent {
    label 'Slave_Induccion'
  }

  //Opciones específicas de Pipeline dentro del Pipeline
  options {
    	buildDiscarder(logRotator(numToKeepStr: '3'))
 		disableConcurrentBuilds()
  }

  //Una sección que define las herramientas “preinstaladas” en Jenkins
  tools {
    jdk 'JDK17_Centos' //Versión preinstalada en la Configuración del Master
  }

  //Aquí comienzan los “ítems” del Pipeline
  stages{
    stage('Checkout') {
      steps{
        echo "------------>Checkout<------------"
        checkout scm
      }
    }

    stage('Compile & Unit Tests') {
      steps{
        echo "------------>Compile & Unit Tests<------------"
        sh 'chmod +x ./java-arquitectura-hexagonal/microservicio/gradlew'
        sh 'java-arquitectura-hexagonal/microservicio/gradlew --b java-arquitectura-hexagonal/microservicio/build.gradle test'
      }
    }

    stage('Static Code Analysis') {
      steps{
        echo '------------>Análisis de código estático<------------'
        sonarqubeMasQualityGatesP(sonarKey:'co.com.ceiba.adn:gimasio-jesus.salcedo',
        sonarName:'"CeibaADN-Gimnasio(jesus.salcedo)"',
        sonarPathProperties:'./sonar-project.properties')
        }
    }

    stage('Build') {
      steps {
        echo "------------>Build<------------"
        sh './java-arquitectura-hexagonal/microservicio/gradlew --b ./java-arquitectura-hexagonal/microservicio/build.gradle build -x test'
      }
    }
      }

      post {
        always {
          echo 'This will always run'
        }
        success {
          echo 'This will run only if successful'
          junit  'java-arquitectura-hexagonal/**/test-results/test/*.xml'
        }
        failure {
          echo 'This will run only if failed'
          mail (to: 'jesus.salcedo@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",body: "Something is wrong with ${env.BUILD_URL}")
        }
        unstable {
          echo 'This will run only if the run was marked as unstable'
        }
        changed {
          echo 'This will run only if the state of the Pipeline has changed'
          echo 'For example, if the Pipeline was previously failing but is now successful'
        }
      }
    }