# Lecture-Docker이미지빌드

## Docker-compose로 일괄실행하기
1. DockerFile 생성(개별 서비스별)

   ```
   FROM openjdk:15-jdk-alpine
   COPY target/*SNAPSHOT.jar app.jar
   EXPOSE 8080
   ENV TZ=Asia/Seoul
   RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
   ENTRYPOINT ["java","-Xmx400M","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=docker"]
   ```
2. appplication.yml의 docker관련 설정 확인 (
   - 각각의 마이크로서비스에서 수정
      ```yaml
      중략
      ---

      server:
      port: 8082

      spring:
      profiles: docker
      cloud:
         stream:
            kafka:
            binder:
      #          brokers: my-kafka:9092
               brokers: kafka:9092
      ```


3. 빌드 파일 생성되어
   - 도커에서 참조하는 jar: target/*SNAPSHOT.jar

      ```bash
      Administrator@SKCC22N00521 MINGW64 /d/APP/GIT-AMF3/backend/lecture (main)
      $ mvn package

      # backend\lecture\target\Lecture-0.0.1-SNAPSHOT.jar
      ```

4. kafka + lecture를 하나의 docker-compose로 Run
   - docker-compose 파일을 root로 이동 헸음(향후 kafka, RDBMS등이 추가 대비)
   - 각각의 마이크로 서비스 추가
      ```yml
      version: '2'

      services:
      lecture:
         build: lecture
         mem_limit: 300m
         ports:
            - 8081:8080
         environment:
            - SPRING_PROFILES_ACTIVE=docker
         depends_on:
            - kafka
      category:
         build: lecturecategory-domain
         mem_limit: 300m
         ports:
            - 8082:8080
         environment:
            - SPRING_PROFILES_ACTIVE=docker
         depends_on:
            - kafka

      zookeeper:
         image: confluentinc/cp-zookeeper:latest
         environment:
            ZOOKEEPER_CLIENT_PORT: 2181
            ZOOKEEPER_TICK_TIME: 2000
         ports:
            - 22181:2181

      kafka:
         image: confluentinc/cp-kafka:latest
         depends_on:
            - zookeeper
         ports:
            - 9092:9092
         environment:
            KAFKA_BROKER_ID: 1
            KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
            KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092
            KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
            KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
            KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
            KAFKA_ADVERTISED_HOST_NAME: kafka

      ```
5. docker-compose로 동시 기동
   - Kafka, zookeeper, leature, category
   - 로그 확인 : 'docker-compose logs lecture' 또는 docker-desktop(권장) 이용
      ```console
      Administrator@SKCC22N00521 MINGW64 /d/APP/GIT-AMF3/backend (main)
      $ docker-compose up
      [+] Building 6.4s (9/9) FINISHED
      => [internal] load build definition from Dockerfile                                                                                                                                                      0.0s
      => => transferring dockerfile: 340B                                                                                                                                                                      0.0s
      => [internal] load .dockerignore                                                                                                                                                                         0.0s
      => => transferring context: 2B                                                                                                                                                                           0.0s
      => [internal] load metadata for docker.io/library/openjdk:15-jdk-alpine                                                                                                                                  2.9s
      => [auth] library/openjdk:pull token for registry-1.docker.io
      ```

```

```
