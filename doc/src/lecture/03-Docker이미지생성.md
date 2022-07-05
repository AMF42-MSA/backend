# Lecture-Docker이미지빌드

## DockerFile 생성
1. 이미 생성되어 있음(나중에 Gatewat가 완성되면 8080으로 변경하고, 일단은 8081로 설정함)

- 변경전
```
FROM openjdk:15-jdk-alpine
COPY target/*SNAPSHOT.jar app.jar
EXPOSE 8080
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Xmx400M","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=docker"]
```

- 변경후
```
FROM openjdk:15-jdk-alpine
COPY target/*SNAPSHOT.jar app.jar
EXPOSE 8081
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Xmx400M","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=docker"]
```

2. 빌드 파일 생성되어
   - 도커에서 참조하는 jar: target/*SNAPSHOT.jar
   
```bash
cd lecture
mvn build
```

```

```

```

```

```

```
