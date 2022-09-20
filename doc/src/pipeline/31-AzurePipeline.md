<style>
.burk {
    background-color: red;
    color: yellow;
    display:inline-block;
}
</style>
# AKS 구성
2022_Kubernetes Operation on Azure LAB.pdf

## 1. 클러스터 배포

### 1.1 Azure Portal 접속
https://portal.azure.com/ 에 접속

### 1.2 Cloud Shell 생성
1. CloudShell 생성
   - bash
   - 구독 확인 후 스토리지 만들기 클릭
2. 클러스터 배포
   - 리소스그룹 생성
        ```bash
        mira [ ~ ]$ az group create --name aks-rg --location koreacentral
        {
        "id": "/subscriptions/7df1fe13-dbee-442e-8ec1-0fd7aaf51f1e/resourceGroups/aks-rg",
        "location": "koreacentral",
        "managedBy": null,
        "name": "aks-rg",
        "properties": {
            "provisioningState": "Succeeded"
        },
        "tags": null,
        "type": "Microsoft.Resources/resourceGroups"
        }
        ```

### 1.3 AKS 클러스터 배포
- 클러스터 배포 (5분 정도 소요)
   ```bash
   $ az aks create --resource-group aks-rg --name \
   aks-cluster --node-count 3 --generate-ssh-keys --enable-node-public-ip
   ```

### 1.4 AKS 클러스터 인증
- 클러스터 정보 인증
   ```bash
   $ az aks get-credentials --resource-group aks-rg --name aks-cluster

   Merged "aks-cluster" as current context in /home/mira/.kube/config
   ```
- 노드 확인
   ```bash
   $ kubectl get nodes

   NAME                                STATUS   ROLES   AGE     VERSION
   aks-nodepool1-32691158-vmss000000   Ready    agent   5m      v1.23.8
   aks-nodepool1-32691158-vmss000001   Ready    agent   4m58s   v1.23.8
   aks-nodepool1-32691158-vmss000002   Ready    agent   5m5s    v1.23.8
   ```
- Kubectl 자동완성 기능 적용
- Tap 키를 사용하여 자동완성 기능 적용,
- kubectl 을 k 로 약어(short name) 설정 ( 이 부분은 잘 안됨)
   ```bash
   source <(kubectl completion bash)
   echo "source <(kubectl completion bash)" >> ~/.bashrc
   source /etc/bash_completion
   alias k=kubectl
   complete -F __start_kubectl k
   ```

## 2. 워크로드 배포part1
리소스 목록 확인
- 해당 명령어를 통해 리소스 목록과 API 버전, Short Name 확인
    ```bash
    $ kubectl api-resources
    NAME                              SHORTNAMES          APIVERSION                             NAMESPACED   KIND
    bindings                                              v1                                     true         Binding
    componentstatuses                 cs                  v1                                     false        ComponentStatus
    configmaps                        cm                  v1                                     true         ConfigMap
    endpoints                         ep                  v1                                     true         Endpoints
    ```

### 2.1 Pod
1. Pod 조회
    ```bash
    $ kubectl get pods
    No resources found in default namespace.
    ```
    ```console
    $ pod.yaml

    apiVersion: v1
    kind: Pod
    metadata:
    name: nginx
    spec:
    containers:
    - name: nginx
        image: nginx:1.14.0
        ports:
        - containerPort: 80
    ```

2. pod 생성
    ```bash
    kubectl create -f pod.yaml
    ```
3. pod 세부 정보 조회
    ```bash
    kubectl get pod -o wide
    kubectl describe pod
    ```
4. pod 정보 확인 후 삭제
    ```bash
    kubectl get pod
    kubectl describe pod
    kubectl delete pod --all
    ```
5. Namespace
    ```console
    # yaml 확인
    $> cat ns1.yaml

    apiVersion: v1
    kind: Namespace
    metadata:
    name: ns1
    ```

    ```bash
    # Namespace 조회
    kubectl get namespace
    #Namespace 생성
    kubectl create -f ns1.yaml
    #Namespace 정보 조회
    kubectl get ns
    ```
6. Namespace안에 Pod 생성


    ```bash
    # Namespace 조회
    kubectl create -f pod.yaml -n ns1
    # pod 조회
    kubectl get pod
    kubectl get pod -n ns1
    # Namespace 삭제
    kubectl delete ns ns1
    ```
### 2.2 Replicaset

1. Replicaset
    cat rs1.yaml
    ```console
    apiVersion: apps/v1
    kind: ReplicaSet
    metadata:
    name: rs1
    spec:
    replicas: 2
    selector:
        matchLabels:
        app: nginx
    template:
        metadata:
        name: rs1-pod
        labels:
            app: nginx
        spec:
        containers:
        - name: nginx
            image: nginx:1.14.0
    ```

    ```bash
    # Replicaset 조회
    kubectl get rs

    #Replicaset 생성
    kubectl create -f rs1.yaml

    # Replicaset 세부 조회
    kubectl get rs
    kubectl describe rs
    ```
2. Replicaset
    ```bash
    # Pod 조회
    kubectl get pod

    #생성된 pod 중1개 삭제 (rs1-qw5rz 조회된 이름)
    kubectl delete pod rs1-qw5rz

    # Pod 자동 복구 확인(// 새롭게 2개 생성 확인)
    kubectl get pod

    #Replicaset 삭제
    kubectl delete rs --all
    ```

### 2.3 Deployment
- yaml 확인(cat dp1.yaml)
    ```console
    apiVersion: apps/v1
    kind: Deployment
    metadata:
    name: dp1
    spec:
    replicas: 3
    strategy:
        type: Recreate
    selector:
        matchLabels:
        app: nginx
    template:
        metadata:
        name: dp1-pod
        labels:
            app: nginx
        spec:
        containers:
        - name: nginx
            image: nginx:1.14.0
            ports:
            - containerPort: 80
    ```
- Deployment생성
    ```bash
    #deployment 조회
    kubectl get deploy

    #deployment 생성
    kubectl create -f dp1.yaml

    #deployment 세부 조회
    kubectl describe deploy
    ```

- 특정 pod 삭제후 복원 확인
    ```bash
    #Pod 조회
    kubectl get pod

    #생성된 pod 중 1개 삭제(pod_name은 조회한 이름으로)
    kubectl delete pod pod_name

    #Pod 자동 복구 확인
    kubectl get pod
    ```

- 세션을 추가해서 변경 모니터링
    ```bash
    watch -n 0.5 kubectl get pod
    ```


```bash
```
```bash
```
```bash
```
```bash
```
```bash
```
```bash
```
```bash
```
```bash
```
```bash
```
```bash
```
```bash
```
