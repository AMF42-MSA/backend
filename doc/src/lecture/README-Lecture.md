# Lecture(강의)-README

## 1. Running in local development environment
1. run
    ```
    mvn spring-boot:run
    ```
2. Packaging and Running in docker environment
    ```
    mvn package -B
    docker build -t username/class:v1 .
    docker run username/class:v1
    ```
3. Push images and running in Kubernetes
    ```
    docker login
    # in case of docker hub, enter your username and password

    docker push username/class:v1
    ```

4. Edit the deployment.yaml under the /kubernetes directory:
    ```
        spec:
        containers:
            - name: class
            image: username/class:latest   # change this image name
            ports:
                - containerPort: 8080

    ```

5. Apply the yaml to the Kubernetes:
    ```
    kubectl apply -f kubernetes/deployment.yml
    ```

6. See the pod status:
    ```
    kubectl get pods -l app=class
    ```

7. If you have no problem, you can connect to the service by opening a proxy between your local and the kubernetes by using this command:
    ```
    # new terminal
    kubectl port-forward deploy/lecture 8081:8081

    # another terminal
    http localhost:8080
    ```

8. If you have any problem on running the pod, you can find the reason by hitting this:
    ```
    kubectl logs -l app=lecture
    ```

Following problems may be occurred:

1. ImgPullBackOff:  Kubernetes failed to pull the image with the image name you've specified at the deployment.yaml. Please check your image name and ensure you have pushed the image properly.
2. CrashLoopBackOff: The spring application is not running properly. If you didn't provide the kafka installation on the kubernetes, the application may crash. Please install kafka firstly:

https://labs.msaez.io/#/courses/cna-full/full-course-cna/ops-utility

## 코드 테스트 방법

1. http로 테스트
   - pip install httpie
    ```
    http :8081/lectures title="MSA" minEnrollment=1 maxEnrollment=10 status="OPENED" categoryId[id]="1"
    -- 결과 조회
    http GET localhost:8081/lectures/1

    http :8082/enrollments customerId="myinno" classId[id]="1" status="ENROLLED"

    http :8083/categories title="MSA_catalog"

    ```
    wsl
    cd /tmp/docker-desktop-root/mnt/host/d/APP/git-amf3/backend#



