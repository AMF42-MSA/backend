#  Kafka  연동

## 1 Kafka  연동 정리
1. 수정한 프로그램 목록
   - AbstractEvent
   - PolicyHandler
   - KafkaProcessor
   - application.yml은
     ```
      group: lecture
      destination: lecture-category
     ```
2. 기능
   - category의 ReadModel  ( 변경 내역을 ) 처리하는 부분
3. 테스트한 내용
   - 로컬에서 수행중
   - "docker-compose exec -it kafka /bin/bash" 수행한 아무런 동작이 없어, docker-desktop의 'CLI' 명령어 입력
   - docker-desktop의 'CLI'또는 'Command Prompt'에서 수행
    ```
    D:\APP\GIT-AMF3\backend>docker-compose exec -it kafka /bin/bash
    [appuser@532852d160fa ~]$ cd /bin
    [appuser@532852d160fa bin]$ ./kafka-console-producer --bootstrap-server localhost:29092 --topic lecture-category
    >{"jobType":"INSERT","id":"100","title":"MSA"}
    >{"jobType":"INSERT","id":"100","title":"MSA"}
    ```
4. 테스트 결과
    ```json
    PS D:\APP\GIT-AMF3\backend\kafka> http GET :8081/categories/1
    HTTP/1.1 200
    Connection: keep-alive
    Content-Type: application/hal+json
    Date: Fri, 01 Jul 2022 07:28:40 GMT
    Keep-Alive: timeout=60
    Transfer-Encoding: chunked
    Vary: Origin
    Vary: Access-Control-Request-Method
    Vary: Access-Control-Request-Headers

    {
        "_links": {
            "category": {
                "href": "http://localhost:8081/categories/1"
            },
            "self": {
                "href": "http://localhost:8081/categories/1"
            }
        },
        "title": "MSA"
    }
    ```
5. 'id'는 입력받은 대로 그대로 입력
    - entity에서 자동생성 부분 제거
    ```
    sh-4.4$ ./kafka-console-producer --bootstrap-server localhost:29092 --topic lecture-category
    >{"jobType":"INSERT","id":"100","title":"MSA"}
    >{"jobType":"INSERT","id":"100","title":"MSA"}
    >{"jobType":"UPDATE","id":"100","title":"MSA-수정"}
    ```
    ``` json
    PS D:\APP\GIT-AMF3\backend\kafka> http GET :8081/categories/100
    HTTP/1.1 200

    {
        "_links": {
            "category": {
                "href": "http://localhost:8081/categories/100"
            },
            "self": {
                "href": "http://localhost:8081/categories/100"
            }
        },
        "title": "MSA"
    }


    PS D:\APP\GIT-AMF3\backend\kafka> http GET :8081/categories/100
    {
        "_links": {
            "category": {
                "href": "http://localhost:8081/categories/100"
            },
        "title": "MSA-수정"
    }

    ```


