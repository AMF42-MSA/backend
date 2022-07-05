# level3-oop-polymorphism

## Base Project 다운로드 및 실행
먼저, 새로운 브라우저 탭을 열고, base project 를 gitpod 로 접속합니다

https://gitpod.io/#https://github.com/msa-school/ddd-petstore-level2-oop-soc

GidPod 내에 터미널을 열고(왼쪽 상단의 햄버거 버튼 > Terminal > New Terminal), 프로젝트가 잘 컴파일 되는지 확인합니다:
```
mvn spring-boot:run
```

## 비기능적 요구사항 (응집도를 높히고 결합도를 낮추어라)
- Cat과 Dog 의 도메인 전문가들이 각자의 관점에서 분리된 Dog 와 Cat 을 관리할 수 있도록 분리
- Cat과 Dog 의 공통된 속성과 행위는 Pet 에서 관리될 수 있도록 함

## 공통된 속성을 Pet 으로 구현
- Pet.java: https://github.com/msa-school/ddd-petstore-level3-oop-polymorphism/blob/main/src/main/java/com/demo/petstore/Pet.java


## Cat 과 Dog 에 각각의 특성을 구현
- Cat.java: https://github.com/msa-school/ddd-petstore-level3-oop-polymorphism/blob/main/src/main/java/com/demo/petstore/Cat.java
- Dog.java: https://github.com/msa-school/ddd-petstore-level3-oop-polymorphism/blob/main/src/main/java/com/demo/petstore/Dog.java

## 다음:  인프라와 도메인의 분리
- https://github.com/msa-school/ddd-petstore-level4-infra-coupled
