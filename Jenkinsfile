pipeline {
  agent {
    label 'remediation'
  }
  stages {
    stage('spotless') {
      steps{
        sh '(mvn spotless:check)'
      }
    }
    stage('build') {
      steps{
        sh '(mvn clean install -DskipTests)'
      }
    }
    stage('deploy') {
      steps {
        sh '(mvn deploy -DskipTests)'
      }
    }
  }
}