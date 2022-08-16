# Node.js & React
Build a Node.js and React app with npm
- https://www.jenkins.io/doc/tutorials/build-a-node-js-and-react-app-with-npm/
- Node Package Manager(npm)
-  "Welcome to React"라는 콘텐츠가 포함된 웹 페이지를 생성하고 애플리케이션이 만족스럽게 렌더링되는지 확인하는 테스트

## Docker 에서 Docker기반으로 수행

중복되는 부분이 대부분임(최종 기동시 "/home" volumn 추가 부분)

- 'A03'과 동일

## 샘플 리포지토리 포크 및 복제

1. simple-node-js-react-npm-app GitHub에서 로컬 GitHub 계정으로 분기
   - https://github.com/jenkins-docs/simple-node-js-react-npm-app
   - [fork]https://github.com/myinno/simple-node-js-react-npm-app
2. GIT소스 docker 서버로 다운로드
   - 리눅스는/home/<your-username>/GitHub/simple-node-js-react-npm-app
    ```bash
    $ pwd
    /home/GitHub
    $ git clone https://github.com/myinno/simple-node-js-react-npm-app
    Cloning into 'simple-node-js-react-npm-app'...
    remote: Enumerating objects: 78, done.
    remote: Total 78 (delta 0), reused 0 (delta 0), pack-reused 78
    Receiving objects: 100% (78/78), 19.91 KiB | 550.00 KiB/s, done.
    Resolving deltas: 100% (25/25), done.
    ```
## Jenkins에서 파이프라인 프로젝트 만들기
Jenkins에 파이프 라인 구축
```console
이름: simple-node-js-react-npm-app
SCM: Git
URL: /home/GitHub/simple-node-js-react-npm-app  <-- 오류 발생
URL: https://github.com/myinno/simple-node-js-react-npm-app.git
```
## Jenkinsfile 생성해서
myinno/simple-node-js-react-npm-app/Jenkinsfile
```bash
pipeline {
    agent {
        docker {
            image 'node:lts-bullseye-slim'
            args '-p 3000:3000'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'npm install'
            }
        }
    }
}
```

최종 변경
```bash
pipeline {
    agent {
        docker {
            image 'node:lts-buster-slim'
            args '-p 3000:3000'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'npm install'
            }
        }
        stage('Test') {
            steps {
                sh './jenkins/scripts/test.sh'
            }
        }
        stage('Deliver') {
            steps {
                sh './jenkins/scripts/deliver.sh'
                input message: 'Finished using the web site? (Click "Proceed" to continue)'
                sh './jenkins/scripts/kill.sh'
            }
        }
    }
}
```

##  Jenkins 실행
 정상 실행은 되나, 결과 확인은..
 ```bash
 http://localhost:3000
 ```
