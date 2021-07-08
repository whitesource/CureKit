pipeline {
  agent {
    label 'remediation'
  }
  stages {
    stage('build') {
      steps{
        sh '(mvn clean install)'
      }
    }
  }
}