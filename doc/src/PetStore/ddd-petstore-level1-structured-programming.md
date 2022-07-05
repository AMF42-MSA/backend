# level1-structured-programming

## Base Project 다운로드 및 실행
먼저, 새로운 브라우저 탭을 열고, springboot-scratch 를 gitpod 로 접속합니다
https://gitpod.io/#https://github.com/msa-school/springboot-scratch

GidPod 내에 터미널을 열고(왼쪽 상단의 햄버거 버튼 > Terminal > New Terminal), 프로젝트가 잘 컴파일 되는지 확인합니다:
```
mvn spring-boot:run
```


## 요구사항:
- Pet의 세부정보는 id, name, 출생주소가 있다
- Pet 은 고양이, 강아지 두 종류가 있다
- Cat은 “야옹”하고 speak 한다.
- Dog는 “멍멍”하고 speak 한다.
- Cat는 eat하면 1씩 energy가 증가한다
- Dog는 eat하면 2씩 energy가 증가한다
- Cat은 그루밍을 해주면 1씩 appearance가 증가한다
- Cat은 sleep하면 energy와 appearance가 1씩 증가한다
- Dog는 sleep하면 1씩 energy만 증가한다


## Petstore 의 model을 클래스로 생성
- Pet.java
```
package com.demo.petstore;

public class Pet {
    int appearance;
    int energy;
    String type;
}
```

- PetstoreApplication.java 의 main 함수 내부를 아래와 같이 구현하고자 한다:
```javascript
	public static void main(String[] args) {

		Pet dog = new Pet();
		dog.appearance = 0;
		dog.energy = 0;
		dog.type = "Dog";

		speak(dog);

		eat(dog);

		System.out.println("appearance=" + dog.appearance);
		System.out.println("energy=" + dog.energy);

		sleep(dog);

		System.out.println("appearance=" + dog.appearance);
		System.out.println("energy=" + dog.energy);

		//SpringApplication.run(PetstoreApplication.class, args);
	}
```

## 문제: 다음과 같은 출력결과를 얻기위하여 PetstoreApplication.java 에 speak, eat, groom 메서드를 구현하시오:
```
멍멍
appearance=0
energy=2
appearance=0
energy=3
```
- 정답: https://github.com/msa-school/ddd-petstore-level1-single-domain-model/blob/main/src/main/java/com/demo/petstore/PetstoreApplication.java

## 해석
- 각 메서드의 구현체 내부는 if-then-else 를 통해서 각 type 별 로직을 수행해야 한다.
- 평가: 구조적 프로그래밍을 통한 Dog, Cat 의 구현은 로직이 분산되는 문제가 존재한다. 
- 각 type 별 해당 로직을 잘 이해하는 전문가들 (Cat을 잘 아는 전문가와 Dog 를 잘 아는 전문가) 이 하나의 파일 - PetstoreApplication.java 내에서 자신의 로직 부분을 찾아서 다른 전문가의 작업영역과 혼재되지 않도록 작성해야 한다. 

## 다음
- Separation Of Concerns: UI (System.out.println)과 비즈니스 로직(Pet 동작)의 분리
- https://github.com/msa-school/ddd-petstore-level2-oop-soc

