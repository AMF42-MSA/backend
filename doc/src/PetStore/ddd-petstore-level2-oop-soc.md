# level2-oop-soc

## Base Project 다운로드 및 실행
먼저, 새로운 브라우저 탭을 열고, springboot-scratch 를 gitpod 로 접속합니다
https://gitpod.io/#https://github.com/msa-school/ddd-petstore-level1-structured-programming

GidPod 내에 터미널을 열고(왼쪽 상단의 햄버거 버튼 > Terminal > New Terminal), 프로젝트가 잘 컴파일 되는지 확인합니다:
```
mvn spring-boot:run
```

## 비기능적 요구사항 (응집도를 높히고 결합도를 낮추어라)
- PetstoreApplication.java 은 UI (System.out.println) 만 개발하고, 
- Pet.java 는 애완동물의 특성만을 구현하도록 하라.

## PetstoreApplication 내에 있던 행위들을 Pet.java 의 인스턴스 행위로 이동
- Pet.java


- PetstoreApplication.java 에서는 Pet.java 를 순수히 호출하여 Pet.java 에 완전히 로직을 위임:

https://github.com/msa-school/ddd-petstore-level2-polymorphism/blob/main/src/main/java/com/demo/petstore/PetstoreApplication.java

- 평가: PetstoreApplication.java (UI 개발) 담당자와 Pet.java (애완동물 전문가)와의 도메인 지식의 분리는 이루어졌다. 하지만, Cat과 Dog 의 도메인 전문가들이 다르다면, 여전히 Pet.java 에 Cat과 Dog 전문가가 Pet.java 를 공통적으로 관리해야 한다.

## 다음:  Polymorphism 을 통한 공통성과 개별특성화

- Pet.java 를 Cat.java 와 Dog.java 로 분리하면서도 PetstoreApplication.java 는 기존의 코드를 유지하시오
- https://github.com/msa-school/ddd-petstore-level3-oop-polymorphism
