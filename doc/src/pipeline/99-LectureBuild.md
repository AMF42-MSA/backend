# GCP 배포
Jenkins on Google Cloud
- https://www.jenkins.io/doc/tutorials/tutorials-for-installing-jenkins-on-Google-Cloud/


## 1. Jenkins 및 GitOps 접근 방식

```bash
    docker run --name jenkins-blueocean --detach ^
    --network jenkins --env DOCKER_HOST=tcp://docker:2376 ^
    --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 ^
    --volume jenkins-data:/var/jenkins_home ^
    --volume jenkins-docker-certs:/certs/client:ro ^
    --volume "d:/APP/GIT-AMF3/backend":/home ^
    --restart=on-failure ^
    --env JAVA_OPTS="-Dhudson.plugins.git.GitSCM.ALLOW_LOCAL_CHECKOUT=true" ^
    --publish 8099:8080 --publish 50000:50000 myjenkins-blueocean:2.346.3-1
```
## 2. Google Compute Engine의 Jenkins
