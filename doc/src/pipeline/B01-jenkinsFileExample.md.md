# Jenkinsfile Example

## 1. Using a Jenkinsfile
1. Creating a Jenkinsfile
```bash
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
```
2. Build
```bash
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'make'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
}
```

3. Test
```bash
pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                /* `make check` returns non-zero on test failures,
                * using `true` to allow the Pipeline to continue nonetheless
                */
                sh 'make check || true'
                junit '**/target/*.xml'
            }
        }
    }
}
```
4. Deploy
```bash
pipeline {
    agent any

    stages {
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS'
              }
            }
            steps {
                sh 'make publish'
            }
        }
    }
}
```
5. Using environment variables
```bash
BUILD_ID
The current build ID, identical to BUILD_NUMBER for builds created in Jenkins versions 1.597+

BUILD_NUMBER
The current build number, such as "153"

BUILD_TAG
String of jenkins-${JOB_NAME}-${BUILD_NUMBER}. Convenient to put into a resource file, a jar file, etc for easier identification

BUILD_URL
The URL where the results of this build can be found (for example http://buildserver/jenkins/job/MyJobName/17/ )

EXECUTOR_NUMBER
The unique number that identifies the current executor (among executors of the same machine) performing this build. This is the number you see in the "build executor status", except that the number starts from 0, not 1

JAVA_HOME
If your job is configured to use a specific JDK, this variable is set to the JAVA_HOME of the specified JDK. When this variable is set, PATH is also updated to include the bin subdirectory of JAVA_HOME

JENKINS_URL
Full URL of Jenkins, such as https://example.com:port/jenkins/ (NOTE: only available if Jenkins URL set in "System Configuration")

JOB_NAME
Name of the project of this build, such as "foo" or "foo/bar".

NODE_NAME
The name of the node the current build is running on. Set to 'master' for the Jenkins controller.

WORKSPACE
The absolute path of the workspace
```
```bash
pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
        }
    }
}
```
6. Setting environment variables
```bash
pipeline {
    agent any
    environment {
        CC = 'clang'
    }
    stages {
        stage('Example') {
            environment {
                DEBUG_FLAGS = '-g'
            }
            steps {
                sh 'printenv'
            }
        }
    }
}
```

7. Setting environment variables dynamically
```bash
pipeline {
    agent any
    environment {
        // Using returnStdout
        CC = """${sh(
                returnStdout: true,
                script: 'echo "clang"'
            )}"""
        // Using returnStatus
        EXIT_STATUS = """${sh(
                returnStatus: true,
                script: 'exit 1'
            )}"""
    }
    stages {
        stage('Example') {
            environment {
                DEBUG_FLAGS = '-g'
            }
            steps {
                sh 'printenv'
            }
        }
    }
}
```

8. Secret text
```bash
pipeline {
    agent {
        // Define agent details here
    }
    environment {
        AWS_ACCESS_KEY_ID     = credentials('jenkins-aws-secret-key-id')
        AWS_SECRET_ACCESS_KEY = credentials('jenkins-aws-secret-access-key')
    }
    stages {
        stage('Example stage 1') {
            steps {
                //
            }
        }
        stage('Example stage 2') {
            steps {
                //
            }
        }
    }
}
```

9.  Usernames and passwords
```bash
environment {
    BITBUCKET_COMMON_CREDS = credentials('jenkins-bitbucket-common-creds')
}
```
```bash
pipeline {
    agent {
        // Define agent details here
    }
    stages {
        stage('Example stage 1') {
            environment {
                BITBUCKET_COMMON_CREDS = credentials('jenkins-bitbucket-common-creds')
            }
            steps {
                //
            }
        }
        stage('Example stage 2') {
            steps {
                //
            }
        }
    }
}
```
10. Secret files
```bash
pipeline {
    agent {
        // Define agent details here
    }
    environment {
        // The MY_KUBECONFIG environment variable will be assigned
        // the value of a temporary file.  For example:
        //   /home/user/.jenkins/workspace/cred_test@tmp/secretFiles/546a5cf3-9b56-4165-a0fd-19e2afe6b31f/kubeconfig.txt
        MY_KUBECONFIG = credentials('my-kubeconfig')
    }
    stages {
        stage('Example stage 1') {
            steps {
                sh("kubectl --kubeconfig $MY_KUBECONFIG get pods")
            }
        }
    }
}
```

11. SSH User Private Key example
```bash
withCredentials(bindings: [sshUserPrivateKey(credentialsId: 'jenkins-ssh-key-for-abc', \
                                             keyFileVariable: 'SSH_KEY_FOR_ABC', \
                                             passphraseVariable: '', \
                                             usernameVariable: '')]) {
  // some block
}
```

12. Certificate example
```bash
withCredentials(bindings: [certificate(aliasVariable: '', \
                                       credentialsId: 'jenkins-certificate-for-xyz', \
                                       keystoreVariable: 'CERTIFICATE_FOR_XYZ', \
                                       passwordVariable: 'XYZ-CERTIFICATE-PASSWORD')]) {
  // some block
}
```
```bash
pipeline {
    agent {
        // define agent details
    }
    stages {
        stage('Example stage 1') {
            steps {
                withCredentials(bindings: [sshUserPrivateKey(credentialsId: 'jenkins-ssh-key-for-abc', \
                                                             keyFileVariable: 'SSH_KEY_FOR_ABC')]) {
                  //
                }
                withCredentials(bindings: [certificate(credentialsId: 'jenkins-certificate-for-xyz', \
                                                       keystoreVariable: 'CERTIFICATE_FOR_XYZ', \
                                                       passwordVariable: 'XYZ-CERTIFICATE-PASSWORD')]) {
                  //
                }
            }
        }
        stage('Example stage 2') {
            steps {
                //
            }
        }
    }
}
```
13. String interpolation
```bash
def username = 'Jenkins'
echo 'Hello Mr. ${username}'
echo "I said, Hello Mr. ${username}"
```
```bash
Hello Mr. ${username}
I said, Hello Mr. Jenkins
```
14. Interpolation of sensitive environment variables
```bash
pipeline {
    agent any
    environment {
        EXAMPLE_CREDS = credentials('example-credentials-id')
    }
    stages {
        stage('Example') {
            steps {
                /* WRONG! */
                sh("curl -u ${EXAMPLE_CREDS_USR}:${EXAMPLE_CREDS_PSW} https://example.com/")
            }
        }
    }
}
```
```bash
pipeline {
    agent any
    environment {
        EXAMPLE_CREDS = credentials('example-credentials-id')
    }
    stages {
        stage('Example') {
            steps {
                /* CORRECT */
                sh('curl -u $EXAMPLE_CREDS_USR:$EXAMPLE_CREDS_PSW https://example.com/')
            }
        }
    }
}
```
15. Injection via interpolation
```bash
pipeline {
  agent any
  parameters {
    string(name: 'STATEMENT', defaultValue: 'hello; ls /', description: 'What should I say?')
  }
  stages {
    stage('Example') {
      steps {
        /* WRONG! */
        sh("echo ${STATEMENT}")
      }
    }
  }
}
```
```bash
pipeline {
  agent any
  parameters {
    string(name: 'STATEMENT', defaultValue: 'hello; ls /', description: 'What should I say?')
  }
  stages {
    stage('Example') {
      steps {
        /* CORRECT */
        sh('echo ${STATEMENT}')
      }
    }
  }
}
```
```bash
pipeline {
  agent any
  environment {
    EXAMPLE_KEY = credentials('example-credentials-id') // Secret value is 'sec%ret'
  }
  stages {
    stage('Example') {
      steps {
          /* WRONG! */
          bat "echo ${EXAMPLE_KEY}"
      }
    }
  }
}
```
```bash
pipeline {
  agent any
  environment {
    EXAMPLE_KEY = credentials('example-credentials-id') // Secret value is 'sec%ret'
  }
  stages {
    stage('Example') {
      steps {
          /* CORRECT */
          bat 'echo %EXAMPLE_KEY%'
      }
    }
  }
}
```

16. Handling parameters
```bash
pipeline {
    agent any
    parameters {
        string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
    }
    stages {
        stage('Example') {
            steps {
                echo "${params.Greeting} World!"
            }
        }
    }
}
```

17. Handling failure
```bash
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'make check'
            }
        }
    }
    post {
        always {
            junit '**/target/*.xml'
        }
        failure {
            mail to: team@example.com, subject: 'The Pipeline failed :('
        }
    }
}
```

18. Using multiple agents
```bash
pipeline {
    agent none
    stages {
        stage('Build') {
            agent any
            steps {
                checkout scm
                sh 'make'
                stash includes: '**/target/*.jar', name: 'app'
            }
        }
        stage('Test on Linux') {
            agent {
                label 'linux'
            }
            steps {
                unstash 'app'
                sh 'make check'
            }
            post {
                always {
                    junit '**/target/*.xml'
                }
            }
        }
        stage('Test on Windows') {
            agent {
                label 'windows'
            }
            steps {
                unstash 'app'
                bat 'make check'
            }
            post {
                always {
                    junit '**/target/*.xml'
                }
            }
        }
    }
}
```

19. Optional step arguments
```bash
git url: 'git://example.com/amazing-project.git', branch: 'master'
git([url: 'git://example.com/amazing-project.git', branch: 'master'])
```
```bash
sh 'echo hello' /* short form  */
sh([script: 'echo hello'])  /* long form */
```
20. Parallel execution
```bash
stage('Build') {
    /* .. snip .. */
}

stage('Test') {
    parallel linux: {
        node('linux') {
            checkout scm
            try {
                unstash 'app'
                sh 'make check'
            }
            finally {
                junit '**/target/*.xml'
            }
        }
    },
    windows: {
        node('windows') {
            /* .. snip .. */
        }
    }
}
```



## 5. Jenkins 및 GitOps 접근 방식
https://www.jenkins.io/doc/book/pipeline/docker/


1. Customizing the execution environment
    ```bash
    pipeline {
        agent {
            docker { image 'node:16.13.1-alpine' }
        }
        stages {
            stage('Test') {
                steps {
                    sh 'node --version'
                }
            }
        }
    }
    ```
2. Workspace synchronization
    ```bash
    pipeline {
        agent any
        stages {
            stage('Build') {
                agent {
                    docker {
                        image 'gradle:6.7-jdk11'
                        // Run the container on the node specified at the
                        // top-level of the Pipeline, in the same workspace,
                        // rather than on a new node entirely:
                        reuseNode true
                    }
                }
                steps {
                    sh 'gradle --version'
                }
            }
        }
    }
    ```
3. Caching data for containers
    ```bash
    pipeline {
        agent {
            docker {
                image 'maven:3.8.1-adoptopenjdk-11'
                args '-v $HOME/.m2:/root/.m2'
            }
        }
        stages {
            stage('Build') {
                steps {
                    sh 'mvn -B'
                }
            }
        }
    }
    ```
4. Using multiple containers
    ```bash
    pipeline {
        agent none
        stages {
            stage('Back-end') {
                agent {
                    docker { image 'maven:3.8.1-adoptopenjdk-11' }
                }
                steps {
                    sh 'mvn --version'
                }
            }
            stage('Front-end') {
                agent {
                    docker { image 'node:16.13.1-alpine' }
                }
                steps {
                    sh 'node --version'
                }
            }
        }
    }
    ```
5. Using a Dockerfile
    ```bash
    Dockerfile
    FROM node:16.13.1-alpine
    RUN apk add -U subversion

    pipeline {
        agent { dockerfile true }
        stages {
            stage('Test') {
                steps {
                    sh 'node --version'
                    sh 'svn --version'
                }
            }
        }
    }

    ```
6. Advanced Usage with Scripted Pipeline Running "sidecar" containers
    ```bash
    node {
        checkout scm
        /*
        * In order to communicate with the MySQL server, this Pipeline explicitly
        * maps the port (`3306`) to a known port on the host machine.
        */
        docker.image('mysql:5').withRun('-e "MYSQL_ROOT_PASSWORD=my-secret-pw"' +
                                        ' -p 3306:3306') { c ->
            /* Wait until mysql service is up */
            sh 'while ! mysqladmin ping -h0.0.0.0 --silent; do sleep 1; done'
            /* Run some tests which require MySQL */
            sh 'make check'
        }
    }
    ```

    ```bash
    node {
        checkout scm
        docker.image('mysql:5').withRun('-e "MYSQL_ROOT_PASSWORD=my-secret-pw"') { c ->
            docker.image('mysql:5').inside("--link ${c.id}:db") {
                /* Wait until mysql service is up */
                sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'
            }
            docker.image('centos:7').inside("--link ${c.id}:db") {
                /*
                * Run some tests which require MySQL, and assume that it is
                * available on the host name `db`
                */
                sh 'make check'
            }
        }
    }
    ```
7. Building containers
    ```bash
    node {
        checkout scm

        def customImage = docker.build("my-image:${env.BUILD_ID}")

        customImage.inside {
            sh 'make test'
        }
    }
    ```

    ```bash
    node {
        checkout scm
        def customImage = docker.build("my-image:${env.BUILD_ID}")
        customImage.push()
    }
    ```
```bash
node {
    checkout scm
    def customImage = docker.build("my-image:${env.BUILD_ID}")
    customImage.push()

    customImage.push('latest')
}
```

```bash
node {
    checkout scm
    def testImage = docker.build("test-image", "./dockerfiles/test")

    testImage.inside {
        sh 'make test'
    }
}
```
8. Using a remote Docker server
```bash
node {
    checkout scm

    docker.withServer('tcp://swarm.example.com:2376', 'swarm-certs') {
        docker.image('mysql:5').withRun('-p 3306:3306') {
            /* do things */
        }
    }
}
```
9.  Using a custom registry
```bash
node {
    checkout scm

    docker.withRegistry('https://registry.example.com') {

        docker.image('my-custom-image').inside {
            sh 'make test'
        }
    }
}
```
```bash
node {
    checkout scm

    docker.withRegistry('https://registry.example.com', 'credentials-id') {

        def customImage = docker.build("my-image:${env.BUILD_ID}")

        /* Push the container to the custom Registry */
        customImage.push()
    }
}
```
## 2. Google Compute Engine의 Jenkins
