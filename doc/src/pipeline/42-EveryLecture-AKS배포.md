# 모두의 강의 AKS 배포
- [AKS 배포(Azure Docs)](https://docs.microsoft.com/ko-kr/azure/aks/tutorial-kubernetes-prepare-app)
- [자체 요약정리](41-AKS애플리케이션배포.md)

EveryLecture를 AKS에 배포해 보자(2022-09-02)

## 1 - AKS 환경 구성
사용하는 자원 이름

|구분|이름|적요|
|:-|:-|:-|
|리소스그룹|myResourceGroup|Azure 배포 관련 리소스 생성을 관리하는 그룹, 나중에 해당 그룹만 제거하면 관련 모두 Resource 정리 가능|
|acrName|acr13myinno|Docker 이미지를 Azuredp 저장하기 위한 저장소, <BR>az acr create로 생성|
|AcrLoginServer|acr13myinno.azurecr.io|docker 이미지 Repository서버 URL|
|AKS|myAKSCluster|Azure에서 서비스하는 kubenetes 서비스|
||||


작업은 BAsh shell 에서 Az CLI를 이용하여 진행했음

## 1 - 컨테이너 레지스트리 만들기
ACR(Azure Container Registry) 인스턴스 만들기
ACR에 대한 컨테이너 이미지 태그 지정: docker push를 Azure로 하기 위하여
ACR에 이미지 업로드
레지스트리의 이미지 보기

### Azure Container Registry 만들기
1. 리소스 그룹 생성
    ```bash
    az group create --name myResourceGroup --location koreacentral
    ```

2. az acr create 명령을 사용하여 Azure Container Registry 인스턴스생성
   - 도커 이미지를 저장하는 Azure에 있는 저장소(30s)
    ```bash
    az acr create --resource-group myResourceGroup --name acr13myinno --sku Basic
    ```
 3. 환경설정
    ```bash
    az login # Sign into an azure account(부라우저에서  로그인 함)
    az account show # See the currently signed-in account.
    # 교육시 사용한 그룹

    AZ_RESOURCE_GROUP=rg-20220906
    AZ_AKS_NAME=myAKSCluster
    AZ_ACR_NAME=acr13myinno

    az configure --defaults group=$AZ_RESOURCE_GROUP
    #az configure --defaults spring=$AZ_SPRING_APPS_NAME

    K8S_NAMESPACE=confluent
    ```

### 컨테이너 레지스트리에 로그인
1. ACR 인스턴스를 사용하려면 먼저 로그인
    ```bash
    az acr login --name $AZ_ACR_NAME
    ```
### 컨테이너 이미지 태그 지정
1. 로컬 이미지 목록
    ```bash
    $ docker images
    REPOSITORY                  TAG         IMAGE ID       CREATED              SIZE
    backend_gateway             latest      e508e700c677   About a minute ago   375MB
    mysql                       5.7         daff57b7d2d1   8 days ago           430MB
    jenkins_jenkins-blueocean   latest      b552f3e66e69   10 days ago          779MB
    myjenkins-blueocean         2.346.3-1   b552f3e66e69   10 days ago          779MB
    docker                      dind        780523c12933   2 weeks ago          310MB
    confluentinc/cp-kafka       7.2.1       d893473a6510   7 weeks ago          782MB
    confluentinc/cp-zookeeper   7.2.1       3f28db6a433d   7 weeks ago          782MB
    ```
2. ACR에 azure-vote-front 컨테이너 이미지를 사용하려면 레지스트리의 로그인 서버 주소를 사용하여 이미지에 태그를 지정해야 합
    ```bash
    $ az acr list --resource-group $AZ_RESOURCE_GROUP --query "[].{acrLoginServer:loginServer}" --output table

    AcrLoginServer
    ----------------------
    acr13myinno.azurecr.io
    ```
3.  acrLoginServer 주소를 사용하여 로컬 이미지에 태그를 지정
    - confluentinc/cp-zookeeper
    - confluentinc/cp-kafka
    - mysql (제거- 서버 설치버전으로)
    - backend_gateway

    ```bash
    docker tag confluentinc/cp-zookeeper:7.2.1 acr13myinno.azurecr.io/cp-zookeeper:7.2.1
    docker tag confluentinc/cp-kafka:7.2.1 acr13myinno.azurecr.io/cp-kafka:7.2.1
    docker tag backend_gateway:latest acr13myinno.azurecr.io/gateway:latest

    docker tag backend_lecture:latest acr13myinno.azurecr.io/lecture:latest
    docker tag backend_member:latest acr13myinno.azurecr.io/member:latest
    docker tag backend_category:latest acr13myinno.azurecr.io/category:latest

    ```
4. 태그가 적용되었는지 확인하려면 docker images
    ```bash
    docker images
    ```
### 레지스트리에 이미지 푸시
1. 빌드 및 태그가 지정된 이미지를 사용하여 이미지를 ACR 인스턴스로 푸시
    ```bash
    docker push acr13myinno.azurecr.io/cp-zookeeper:7.2.1
    docker push acr13myinno.azurecr.io/cp-kafka:7.2.1
    docker push acr13myinno.azurecr.io/gateway:latest

    docker push acr13myinno.azurecr.io/lecture:latest
    docker push acr13myinno.azurecr.io/member:latest
    docker push acr13myinno.azurecr.io/category:latest
    ```
### 레지스트리에서 이미지 나열
1. CR 인스턴스로 푸시된 이미지 목록을 반환 (push한 목록이 보여야 함)
    ```bash
    az acr repository list --name $AZ_ACR_NAME --output table
    ```
2. 특정 이미지의 태그 확인
    ```bash
    $ az acr repository list --name $AZ_ACR_NAME --output table
    Result
    ---------------
    gateway
    cp-kafka
    cp-zookeeper
    mysql
    ```
## 3 - Kubernetes 클러스터 만들기
- Azure container registry에 인증할 수 있는 Kubernetes AKS 클러스터 배포
- Kubernetes CLI(kubectl) 설치
- AKS 클러스터에 연결하도록 kubectl 구성

### Kubernetes 클러스터 만들기
1. az aks create 명령을 사용하여 AKS 클러스터를 만듭
    ```bash
    az aks create \
        --resource-group $AZ_RESOURCE_GROUP \
        --name $AZ_AKS_NAME \
        --node-count 2 \
        --generate-ssh-keys \
        --attach-acr $AZ_ACR_NAME
    ```

   - 기존 AKS 클러스터에 대한 ACR 통합 구성
    ```bash
    az aks update -n $AZ_AKS_NAME -g $AZ_RESOURCE_GROUP --attach-acr $AZ_ACR_NAME
    ```
### Kubernetes CLI 설치
1. az aks install-cli 명령을 사용하여 kubectl을 로컬로 설치
   - Azure Cloud Shell을 사용하는 경우 kubectl이 이미 설치되어 있습
   - 이전 설치 이력이 있으면 아래 디렉토리 삭제후 작업(c:\Users\Administrator\.azure-kubelogin\,  .azure-kubectl)    \
    ```bash
    az aks install-cli
    ```
### kubectl을 사용하여 클러스터에 연결
 1. Kubernetes 클러스터에 연결하도록 kubectl을 구성
    - 아래 작업중 오류가 발생하면 아래 "C:\Users\Administrator\.kube\config" 삭제후 처리
    -
    ```bash
    az aks get-credentials --resource-group myResourceGroup --name myAKSCluster
    ```
2. 클러스터 노드 목록 (기본으로 2개 출력됨)
    ```bash
    kubectl get nodes
    ```
## 4 - 애플리케이션 실행
- Kubernetes 매니페스트 파일 업데이트
- Kubernetes에서 애플리케이션 실행
- 애플리케이션 테스트
### 매니페스트 파일 업데이트
1. ACR(Azure Container Registry) 인스턴스에 애플리케이션 컨테이너 이미지를 저장
   - 애플리케이션을 배포하려면 ACR 로그인 서버 이름을 포함하도록 Kubernetes 매니페스트 파일에서 이미지 이름을 업데이트해야 합니다.
    ```bash
    $ az acr list --resource-group rg-20220906 --query "[].{acrLoginServer:loginServer}" --output table

    AcrLoginServer
    ----------------------
    acr13myinno.azurecr.io
    ```
2. 매니페스트 파일 수정
   - k8s-infra.yml
    ```bash
    # 주요 부분만 표시함
    신규 작성 docker --> kubectl
    ```
### 애플리케이션 배포
1. 작업대상 NameSpace지정
    ```bash
    kubectl config set-context --current --namespace $K8S_NAMESPACE
    ```
2. 배포하려면 kubectl apply 명령을 사용합니다. 이 명령은 매니페스트 파일을 구문 분석하고 정의된 Kubernetes 개체를 만듭
    ```bash
    $ kubectl apply -f k8s-kafka.yml
    $ kubectl apply -f k8s-gateway.yml
    $ kubectl apply -f k8s-member.yml
    $ kubectl apply -f k8s-category.yml
    $ kubectl apply -f k8s-lecture.yml
    ```
### 애플리케이션 테스트
1. 진행 상태를 모니터링하려
    ```bash
    kubectl get service azure-gateway --watch
    ```
2. 애플리케이션이 실제로 작동하는 모습을 보려면 웹 브라우저를 서비스의 외부 IP 주소로 엽니다
   - EXTERNAL-IP로 브라우저에서 테스트(80 port)
   - 로컬과 동일기능이 되는지 확인

## 5 - 애플리케이션 크기 조정
- Kubernetes 노드 크기 조정
- 애플리케이션을 실행하는 Kubernetes Pod의 크기를 수동으로 조정
- 앱 프런트 엔드를 실행하는 Pod 자동 크기 조정 구성

### 수동으로 Pod 크기 조정
1. Pod의 수와 상태를 확인
    ```bash
    kubectl get pods
    ```
2.  프런트 엔드 Pod 수를 5로
    ```bash
    kubectl scale --replicas=5 deployment/azure-vote-front
    ```
### Pod 자동 크기 조정
1. 메트릭 서버는 Kubernetes에 리소스 사용률을 제공하는 데 사용되며, AKS 클러스터 버전 1.10 이상에서 자동으로 배포됩니다.
    ```bash
    az aks show --resource-group myResourceGroup --name myAKSCluster --query kubernetesVersion --output table
    ```
2. azure-vote-front 배포에서 프런트 엔드 컨테이너는 0.25 CPU를 요청하며 제한은 0.5 CPU
    ```bash
    containers:
    - name: azure-vote-front
        image: mcr.microsoft.com/azuredocs/azure-vote-front:v1
        ports:
        - containerPort: 80
        resources:
        requests:
            cpu: 250m
        limits:
            cpu: 500m
    ```
3. kubectl autoscale 명령을 사용하여 azure-vote-front 배포의 Pod 수를 자동으로 조정
    ```bash
    kubectl autoscale deployment azure-vote-front --cpu-percent=50 --min=3 --max=10
    ```
4. 또는 매니페스트 파일을 만들어 자동 크기 조정기 동작 및 리소스 제한을 정의
    azure-vote-hpa.yaml
    ```bash
    apiVersion: autoscaling/v1
    kind: HorizontalPodAutoscaler
    metadata:
    name: azure-vote-back-hpa
    spec:
    maxReplicas: 10 # define max replica count
    minReplicas: 3  # define min replica count
    scaleTargetRef:
        apiVersion: apps/v1
        kind: Deployment
        name: azure-vote-back
    targetCPUUtilizationPercentage: 50 # target CPU utilization

    ---

    apiVersion: autoscaling/v1
    kind: HorizontalPodAutoscaler
    metadata:
    name: azure-vote-front-hpa
    spec:
    maxReplicas: 10 # define max replica count
    minReplicas: 3  # define min replica count
    scaleTargetRef:
        apiVersion: apps/v1
        kind: Deployment
        name: azure-vote-front
    targetCPUUtilizationPercentage: 50 # target CPU utilization
    ```
5. kubectl apply를 사용하여 azure-vote-hpa.yaml 매니페스트 파일에 정의된 자동 크기 조정기를 적용
    ```bash
    kubectl apply -f azure-vote-hpa.yaml
    ```
6. 자동 조정기의 상태를 확인
    ```bash
    kubectl get hpa

    NAME               REFERENCE                     TARGETS    MINPODS   MAXPODS   REPLICAS   AGE
    azure-vote-front   Deployment/azure-vote-front   0% / 50%   3         10        3          2m
    ```
### 수동으로 AKS 노드 크기 조정
1. Kubernetes 클러스터의 노드 수를 3개로 늘립니다
    ```bash
    az aks scale --resource-group myResourceGroup --name myAKSCluster --node-count 3
    ```
## 6 - 애플리케이션 업데이트
- 프런트 엔드 애플리케이션 코드 업데이트
- 업데이트된 컨테이너 이미지 만들기
- Azure Container Registry에 컨테이너 이미지 푸시
- 업데이트된 컨테이너 이미지 배포

### 애플리케이션 업데이트
업무 수정
1. config_file.cfg 수정
    ```bash
    vi azure-vote/azure-vote/config_file.cfg

    # UI Configurations
    TITLE = 'Azure Voting App'
    VOTE1VALUE = 'Blue'
    VOTE2VALUE = 'Purple'
    SHOWHOST = 'false'
    ```
### 컨테이너 이미지 업데이트
1. 프런트 엔드 이미지를 다시 만들고 업데이트된 애플리케이션을 테스트하려면 docker-compose 명령을 사용합니다. --build 인수를 사용
    ```bash
    docker-compose up --build -d
    ```
### 로컬에서 애플리케이션 테스트
1. http://localhost:8080
   - 변경된 화면 확인

### 이미지 태그 지정 및 밀어넣기
1. 업데이트된 이미지를 올바르게 사용하려면 azure-vote-front 이미지에 ACR 레지스트리의 로그인 서버 이름을 태그로 지정
    ```bash
    az acr list --resource-group myResourceGroup --query "[].{acrLoginServer:loginServer}" --output table
    ```

2. 이미지 버전을 :v2로 업데이트
    ```bash
    docker tag mcr.microsoft.com/azuredocs/azure-vote-front:v1 acr13myinno.azurecr.io/azure-vote-front:v2
    ```
3. 이미지를 업로드
    ```bash
    docker push acr13myinno.azurecr.io/azure-vote-front:v2
    ```
### 업데이트된 애플리케이션 배포
1. kubectl get pods 명령을 사용하여 실행 중인 프런트 엔드 인스턴스 수를 확인
    ```bash
    $ kubectl get pods

    NAME                               READY     STATUS    RESTARTS   AGE
    azure-vote-back-217588096-5w632    1/1       Running   0          10m
    azure-vote-front-233282510-b5pkz   1/1       Running   0          10m
    azure-vote-front-233282510-dhrtr   1/1       Running   0          10m
    azure-vote-front-233282510-pqbfk   1/1       Running   0          10m
    ```

2. azure-vote-front 배포를 확장
    ```bash
    kubectl scale --replicas=3 deployment/azure-vote-front
    ```
3. 애플리케이션을 업데이트하려면 kubectl set 명령을 사용
    ```bash
    kubectl set image deployment azure-vote-front azure-vote-front=acr13myinno.azurecr.io/azure-vote-front:v2
    ```
4. 업데이트된 애플리케이션이 배포되면 Pod가 종료되고 새 컨테이너 이미지로 다
    ```bash
    kubectl get pods
    ```
### 업데이트된 애플리케이션 테스트
1. azure-vote-front 서비스의 외부 IP 주소를 가져옵니다
    ```bash
    kubectl get service azure-vote-front
    ```
## 7- 클러스터 업그레이드
- 현재 및 사용 가능한 Kubernetes 버전 확인
- Kubernetes 노드 업그레이드
- 성공적인 업그레이드의 유효성 검사
- (이 부분은 테스트 하지 않음: 2022-09-01)

### 사용 가능한 클러스터 버전 가져오기
1. 클러스터를 업그레이드하려면 먼저 az aks get-upgrades 명령을 사용하여 업그레이드할 수 있는 Kubernetes 릴리스를 확인
    ```bash
    az aks get-upgrades --resource-group myResourceGroup --name myAKSCluster
    ```
### 클러스터 업그레이드
이 프로세스에서 다음 단계가 수행

1. Kubernetes 스케줄러는 업그레이드될 노드에서 추가 Pod가 예약되지 않도록 합니다.
2. 노드에서 실행 중인 Pod가 클러스터의 다른 노드에서 예약됩니다.
3. 최신 Kubernetes 구성 요소를 실행하는 노드가 만들어집니다.
4. 새 노드가 준비되고 클러스터에 조인될 때 Kubernetes 스케줄러에서 Pod를 실행하기 시작합니다.
5. 이전 노드가 삭제되고, 클러스터의 다음 노드가 차단 및 드레이닝 프로세스를 시작합니다.

1. az aks upgrade 명령을 사용하여 AKS 클러스터를 업그레이드
```bash
az aks upgrade \
    --resource-group myResourceGroup \
    --name myAKSCluster \
    --kubernetes-version KUBERNETES_VERSION
```

### 업그레이드 유효성 검사
1. az aks show 명령을 사용하여 업그레이드가 성공했는지 확인합니다.
```bash
az aks show --resource-group myResourceGroup --name myAKSCluster --output table
```

### 클러스터 삭제
Kubernetes 노드가 Azure VM(가상 머신)에서 실행되므로 클러스터를 사용하지 않더라도 요금이 계속 부과

 az group delete 명령을 사용하여 리소스 그룹, 컨테이너 서비스 및 모든 관련 리소스를 제거
```bash
az group delete --name myResourceGroup --yes --no-wait
```





kafka 관련 아래 정리 (2022-09-06)
https://docs.confluent.io/operator/current/co-quickstart.html
Confluent for Kubernetes Quickstart

namespace 삭제
https://computingforgeeks.com/how-to-force-delete-a-kubernetes-namespace/
How to force delete a Kubernetes Namespace
