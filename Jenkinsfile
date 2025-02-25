pipeline:
  agent: any
  stages:
    - stage: Checkout Code
      steps:
        - script: >
            git branch: 'main',
                credentialsId: 'gittoken',
                url: 'https://github.com/officialdeepurajagopal/PlaywrightApiFramework.git'
    - stage: Build and Test
      steps:
        - script: |
            sh 'mvn clean test'
  post:
    always:
      steps:
        - script: |
            junit '**/target/surefire-reports/*.xml'