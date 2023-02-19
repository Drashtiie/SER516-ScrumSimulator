#!/usr/bin/env groovy

node ('master'){
    stage('Build and Test') {
        properties([pipelineTriggers([[$class: 'GitHubPushTrigger'], pollSCM('H/15 * * * *')])])
        checkout scm
        env.PATH = "${tool 'Maven 3'}/bin:${env.PATH}"
        sh 'mvn clean package'
    }
}
