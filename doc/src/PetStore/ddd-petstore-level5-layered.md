# level5-layered

## Base Project 다운로드 및 실행
먼저, 새로운 브라우저 탭을 열고, base project 를 gitpod 로 접속합니다
https://gitpod.io/#https://github.com/msa-school/ddd-petstore-level4-infra-coupled

GidPod 내에 터미널을 열고(왼쪽 상단의 햄버거 버튼 > Terminal > New Terminal), 프로젝트가 잘 컴파일 되는지 확인합니다:
```
mvn spring-boot:run
```

## 미션
- 인프라 구현에 관련된 코드들 (UI, Database) 과 도메인 언어 (Pet Cat Dog) 를 패키지 분리하라
- 도메인 코드에서 인프라 코드 쪽으로의 어떠한 참조가 없어야 한다 (역방향은 가능) - Dependency Inversion Principle.
- 인프라 없이 도메인 코드만으로도 동작할 수 있어야 한다. 

## 다음: Spring 프레임워크를 통한 Infra Layer 의 자동생성
- JPA를 통한 Database Input/Output Adapter 자동생성
- Spring Data REST 를 통한 REST API Input/Output Adaptor 자동생성

- https://github.com/msa-school/ddd-petstore-level6-layered-spring-jpa
