# Pipeline
- https://www.jenkins.io/doc/book/pipeline/

- Jenkins Pipeline은
  - 지속적 전달 파이프라인을 Jenkins에 구현하고 통합하는 것을 지원하는 플러그인 모음
  - ![](images/jenkins-pipeline-01.png)
## Pipoeline 시작하기

### 선언적 파이프라인 기본 사항
```DSL
pipeline {
    agent any                 <--(1)
    stages {
        stage('Build') {      <--(2)
            steps {
                //            <--(3)
            }
        }
        stage('Test') {
            steps {
                //
            }
        }
        stage('Deploy') {
            steps {
                //
            }
        }
    }
}
```
1. 사용 가능한 에이전트에서 이 파이프라인 또는 해당 단계를 실행
2. 	"빌드" 단계를 정의
3. 	"빌드" 단계와 관련된 몇 가지 단계를 수행

## Getting started with Pipeline

### example-pipeline 따라하기
1. Jenkins 클래식 UI를 통해 기본 파이프라인 생성
    ```bash
    pipeline {
        agent any
        stages {
            stage('Stage 1') {
                steps {
                    echo 'Hello world!'
                }
            }
        }
    }
    ```
### SCM
"Jenkinsfile"을 형상에서 관리하고, 필요시 Checkout 받는 구성으로 진행

## 2. Using a Jenkinsfile
### 2.1 환경 변수 사용

- BUILD_ID
  - Jenkins 버전 1.597 이상에서 생성된 빌드의 BUILD_NUMBER와 동일한 현재 빌드 ID
- 빌드 번호
  - 현재 빌드 번호(예: "153")
- BUILD_TAG
  - 젠킨스-${JOB_ NAME}-${BUILD_NUMBER} 문자열. 리소스 파일, jar 파일 등에 넣어 쉽게 식별 가능
- BUILD_URL
  - 이 빌드의 결과를 찾을 수 있는 URL(예: http://buildserver/jenkins/job/MyJobName/17/ )
- EXECUTOR_NUMBER
  - 이 빌드를 수행하는 현재 실행기를 식별하는 고유 번호(동일한 시스템의 실행기 중에서). 이것은 숫자가 1이 아닌 0부터 시작한다는 점을 제외하고 "빌드 실행기 상태"에 표시되는 숫자입니다.
- 자바_홈
  - 작업이 특정 JDK를 사용하도록 구성된 경우 이 변수는 지정된 JDK의 JAVA_HOME으로 설정됩니다. 이 변수가 설정되면 JAVA_HOME의 bin 하위 디렉토리를 포함하도록 PATH도 업데이트됩니다.
- JENKINS_URL
  - https://example.com:port/jenkins/와 같은 Jenkins의 전체 URL(참고: Jenkins URL이 "시스템 구성"에 설정된 경우에만 사용 가능)
- 직업 이름
  - "foo" 또는 "foo/bar"와 같은 이 빌드의 프로젝트 이름입니다.
- NODE_NAME
  - 현재 빌드가 실행 중인 노드의 이름입니다. Jenkins 컨트롤러에 대해 '마스터'로 설정합니다.
- WORKSPACE
  - 작업 공간의 절대 경로
 - 예시
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


    [Pipeline] { (Example)
    [Pipeline] echo
    Running 4 on http://127.0.0.1:8090/
    ```


1. Setting environment variables(환경 변수 설정)

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
    ```console
    # 밑부분을 확인
    [Pipeline] stage
    [Pipeline] { (Example)
    [Pipeline] withEnv
    [Pipeline] {
    [Pipeline] sh
    + printenv
    JENKINS_HOME=/var/jenkins_home
    JENKINS_UC_EXPERIMENTAL=https://updates.jenkins.io/experimental
    CI=true
    RUN_CHANGES_DISPLAY_URL=http://127.0.0.1:8090/job/example-pipeline/5/display/redirect?page=changes
    HOSTNAME=ccc115c931dd
    NODE_LABELS=built-in
    HUDSON_URL=http://127.0.0.1:8090/
    SHLVL=0
    HOME=/var/jenkins_home
    BUILD_URL=http://127.0.0.1:8090/job/example-pipeline/5/
    HUDSON_COOKIE=7bd64f99-9bf7-4e3b-9d64-2684363baf63
    JENKINS_SERVER_COOKIE=durable-4b39a585f1770abf8d812fc0a243a0e9b9b88eb610368772fcdc2e2f2ba25d4e
    JENKINS_UC=https://updates.jenkins.io
    WORKSPACE=/var/jenkins_home/workspace/example-pipeline
    DOCKER_TLS_VERIFY=1
    REF=/usr/share/jenkins/ref
    NODE_NAME=built-in
    RUN_ARTIFACTS_DISPLAY_URL=http://127.0.0.1:8090/job/example-pipeline/5/display/redirect?page=artifacts
    STAGE_NAME=Example
    EXECUTOR_NUMBER=1
    JENKINS_VERSION=2.346.3
    RUN_TESTS_DISPLAY_URL=http://127.0.0.1:8090/job/example-pipeline/5/display/redirect?page=tests
    JENKINS_INCREMENTALS_REPO_MIRROR=https://repo.jenkins-ci.org/incrementals
    BUILD_DISPLAY_NAME=#5
    HUDSON_HOME=/var/jenkins_home
    DOCKER_CERT_PATH=C:/Program Files/Git/certs/client
    JOB_BASE_NAME=example-pipeline
    PATH=/opt/java/openjdk/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
    BUILD_ID=5
    BUILD_TAG=jenkins-example-pipeline-5
    LANG=C.UTF-8
    JENKINS_URL=http://127.0.0.1:8090/
    JOB_URL=http://127.0.0.1:8090/job/example-pipeline/
    BUILD_NUMBER=5
    JENKINS_NODE_COOKIE=89d087e8-ce28-4b91-9fe0-45f8d3a3b394
    RUN_DISPLAY_URL=http://127.0.0.1:8090/job/example-pipeline/5/display/redirect
    JENKINS_SLAVE_AGENT_PORT=50000
    HUDSON_SERVER_COOKIE=2c2b65d99bdb70c5
    DOCKER_HOST=tcp://docker:2376
    JOB_DISPLAY_URL=http://127.0.0.1:8090/job/example-pipeline/display/redirect
    JOB_NAME=example-pipeline
    COPY_REFERENCE_FILE_LOG=/var/jenkins_home/copy_reference_file.log
    JAVA_HOME=/opt/java/openjdk
    PWD=/var/jenkins_home/workspace/example-pipeline
    DEBUG_FLAGS=-g
    WORKSPACE_TMP=/var/jenkins_home/workspace/example-pipeline@tmp
    CC=clang
    ```

2. 동적(dynamically)으로 환경 변수 설정
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
4. 자격 증명 처리
   - 비밀 텍스트, 사용자 이름과 암호, 비밀 파일의 경우
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

5. 문자열 보간
   - Groovy는 작은 따옴표 또는 큰 따옴표로 문자열 선언을 지원
      ```bash
      def singlyQuoted = 'Hello'
      def doublyQuoted = "World"
      ```
   - 후자 문자열만 달러 기호( $) 기반 문자열 보간을 지원
      ```bash
      def username = 'Jenkins'
      echo 'Hello Mr. ${username}'
      echo "I said, Hello Mr. ${username}"


      #결과
      Hello Mr. ${username}
      I said, Hello Mr. Jenkins
      ```
6. 여러 에이전트 사용
   - "빌드" 단계는 하나의 에이전트에서 수행되고 빌드된 결과는 "테스트" 단계에서 각각 "linux" 및 "windows" 레이블이 지정된 두 개의 후속 에이전트에서 재사용
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

7. 병렬 실행
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

## 브랜치 및 풀 리퀘스트

## Using Docker with Pipeline

### 실행 환경 커스터마이징
  ```dockerfile
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
### 작업 공간 동기화

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
