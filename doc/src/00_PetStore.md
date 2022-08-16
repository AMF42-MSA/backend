# MSA모델링9단계
- backend 교육 정리
- 2022-06-20 ~ 2022-07-01

## 참조한 자료

- https://github.com/msa-school
- https://github.com/msa-school
## Backend 교제

- 교재:
  - https://drive.google.com/file/d/1U82XikdTBmJ_AXI-JbIEKjVLLwVkRZv3/view?usp=sharing
- 이론
  - 파트1
    - https://youtu.be/VSbQ0z5UYaA
  - 파트2 및 환경구성 실습
    - https://youtu.be/uVZ9FfjlNkE
- 실습 리소스
  - 환경구성 (클라우드 IDE 사용)
    - GitPod 클라우드 IDE (VS Code) 사용시
      - 설치 불필요, 웹 브라우저만 있으면 됨
        - 접속 후, 내부에서 Java Language Support 설치 필수
          - 설치하겠다는 자동 메시지 팝업시 꼭 "Install" 선택
            - 설치후 Window Refresh 하겠다는 메시지에서 꼭 "Refresh" 선택
        - 플러그인 설치:
          - https://chrome.google.com/webstore/detail/gitpod-always-ready-to-co/dodmmooeoklaejobgleioelladacbeki
          - 설치가 안되면, 해당 레포 주소창에서 gitpod.io/# 을 앞에 부착한 후, 접속
            - e.g. https://gitpod.io/#https://github.com/msa-school/ddd-petstore-level6-layered-spring-jpa
    - 로컬 구성시 - VS Code 설치
      - VS Code 설치
        - https://www.lainyzine.com/ko/article/how-to-install-visual-studio-code-on-windows-10/#:~:text=%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C%20%EB%B2%84%ED%8A%BC%EC%9D%84%20%ED%81%B4%EB%A6%AD%ED%95%98%EB%A9%B4,%EC%9E%90%EB%8F%99%EC%9C%BC%EB%A1%9C%20%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C%20%EB%B0%9B%EC%95%84%EC%A7%91%EB%8B%88%EB%8B%A4.&text=%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C%20%EB%B0%9B%EC%9D%80%20VSCodeUserSetup%2Dxxx.exe,%EC%84%A4%EC%B9%98%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8%EC%9D%B4%20%EC%8B%A4%ED%96%89%EB%90%A9%EB%8B%88%EB%8B%A4.
    - Java 환경 설치
        - https://code.visualstudio.com/docs/java/java-tutorial
- 자주 쓰는 스크립트
  - 기본 유틸리티 설치:
    ```
    sudo apt-get update
    sudo apt-get install net-tools
    sudo apt install iputils-ping
    pip install httpie

    ```
  - 실행 프로세스 찾기:
    ```
    netstat -lntp | grep :808
    ```
  - 실행 프로세스 삭제:
    ```
    kill -9 <pid>
    ```
- 단계별 소스
  - Step0 - 스프링 부트 프로젝트 컴파일과 실행 방법 이해 
    - https://github.com/msa-school/springboot-scratch
  - Step1 - 구조적 프로그래밍 
    - 영상: https://youtu.be/bca8LnfDdr8
  - Step1 - 구조적 프로그래밍 
    - 영상: https://youtu.be/bca8LnfDdr8
    - https://github.com/msa-school/ddd-petstore-level1-structured-programming
  - Step2 - 객체지향, 관심사 분리 (UI 와 전문지식 분리) 
    - https://github.com/msa-school/ddd-petstore-level2-oop-soc
  - Step3 - 도메인 모델, 폴리몰피즘 (도메인 전문 지식들의 분리)
    - 영상: https://youtu.be/1H67WGGSl0o
    - https://github.com/msa-school/ddd-petstore-level3-oop-polymorphism
    - Class Diagram  - 
        - https://labs.msaez.io/#/uml/nZJ2QhwVc4NlVJPbtTkZ8x9jclF2/753c8c71fe98338c75b8325f90ebb496
  - Step4 - 데이터베이스 구현 (도메인 모델과 인프라 분리 X)
    - https://github.com/msa-school/ddd-petstore-level4-infra-coupled
  - Step5 - Clean Architecture  
    - https://github.com/msa-school/ddd-petstore-level5-layered
  - Step6 - JPA 의 적용
    - 영상: https://youtu.be/S9EnR-loIuA
    - https://github.com/msa-school/ddd-petstore-level6-layered-spring-jpa
    - gitpod:  https://gitpod.io/#https://github.com/msa-school/ddd-petstore-level6-layered-spring-jpa
    - 먼저, Java Language Support 를 설치해준다.
      - 설치안된경우, Extensions > 검색 "Java" > Language Support for Java 를 install
    - MySQLRepository.java 와 OracleRepository.java 를 삭제한 후 실행 (mvn spring-boot:run)
      - 다음 오류의 확인:
        - org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.demo.petstore.domain.Repository' available
    - 정답 코드들과 현재 코드들을 비교하면서 채워넣기: https://github.com/msa-school/ddd-petstore-level6-layered-spring-jpa/tree/main/src/main/java/com/demo/petstore/domain 
      ```
      Repository.java
      Pet.java
      Dog.java
      Cat.java
      ```
    - Repository 구현체를 스프링이 자동생성해줌을 확인
      - Pet.java 의 save()에 다음을 추가:
        ```
        public void save(){        
            Repository repository = PetstoreApplication.getApplicationContext().getBean(Repository.class);
            System.out.println(repository);
            repository.save(this);    
        }
        ```
      - 재실행한 후, 로그를 확인:
        ```
        멍멍
        pet is eating
        appearance = 0 & energy = 2
        pet is sleeping
        appearance = 0 & energy = 3
        org.springframework.data.jpa.repository.support.SimpleJpaRepository@9a37591
        ```
        - SimpleJpaRepository 의 인스턴스가 채택된 것을 확인
- <실습> 각 프로젝트별 도메인 모델 생성
  - https://github.com/msa-school/ddd-petstore-level6-layered-spring-jpa 를 Fork
    - github 로그인
    - 상단 우측의 "Fork" 버튼 클릭
    - 나의 레포지토리로 이동
  - Settings 에서 프로젝트 명 변경
  - Gitpod VSCode 에 접속: 
    - 레포지토리의 url앞에 "https://gitpod.io/#" 붙여서 접속
    - Install Java Language Support 다이얼로그가 뜨면 Install 버튼 클릭
    - Reload window? 나오면 Yes
  - 변경사항 커밋하기
    - README.md 를 수정
    - 왼쪽 Source Control 메뉴 선택 > Changes 의 + 버튼 클릭하여 Staged Changes 들로 전환
    - 상단의 "Message" 박스에 "first commit"으로 커밋로그 입력 후 "V" 체크
    - 하단에 생성되는 "Sync" 버튼 클릭하여 변경사항 push
    - Fork 한 레포지토리에 재접속하여 변경사항 반영 확인
  - 각 프로젝트 별 구현 결과
    - Talent 도메인 - 
      - https://github.com/jinyoung/amf-talent
- Step7 - Big Ball of Mud (Monolith)
  - 영상: part 1:     https://youtu.be/Ra0vQelXBAg
  - part 2: https://youtu.be/1wT63e3_GjA
  - https://github.com/msa-school/ddd-petstore-level7-big-ball-of-mud
  - God Class / God Table --> Pet.java  /  Table Pet
  - Relation Types
      ```
      @OneToMany
      @ManyToOne
      @ManyToMany
      @OneToOne
      ```
  - UML: https://labs.msaez.io/#/uml/nZJ2QhwVc4NlVJPbtTkZ8x9jclF2/753c8c71fe98338c75b8325f90ebb496
    
- Step8 - Resource Firstly 한 REST API 선언과 Input Adaptor 생성, Aggregate Port Method 의 호출
  - 영상: https://youtu.be/8AxL_5q0Zeg
    - REST 개념 설명 동영상
      - 다형성에 대한 REST API 생성: https://youtu.be/tjEX9uEsdD0
      - REST API 의 자동생성 개념: https://youtu.be/XlUs0aVIZZY
      - REST API 로 생성할 수 없는 API 들에 대한 보완 or 자동생성된 API 들에 대한 로직 주입
        - https://youtu.be/iuo_AdX4qPs
  - https://github.com/msa-school/ddd-petstore-level8-rest-api-and-aggregate-port
  - <과정 리뷰> 
    - https://youtu.be/dqVOG_YOFH8
- Step9 - Bounded Multi Models
  - https://github.com/msa-school/ddd-petstore-level9-bounded-multi-model
  - 영상
    - 과정영상: https://youtu.be/DPkZf6Acpfo
    - 각 마이크로서비스 입장에서 단위 개발시에 JSON 으로 카프카 연동하는 방법
      - https://youtu.be/1kpJu-mxaEw
    - 과정 동영상 (old)
      - https://youtu.be/MkFzYXWy-tg
      - https://youtu.be/83OZohBvi1s
  - 싱글모델의 구현
    - 싱글 모델 내부의 요구사항에만 집중
      - 외래에서 요구되는 객체 (엔티티)중, 대표적인 것들만 내부에 선언
      - 이때 외래 객체의 정의도 내 개념, 내 생각대로 그냥 선언하면 됨
        - 클래스명
          - 내 모델에서 불렀을 때의 흔한말 (유비쿼터스 언어)를 사용
        - 필드명
          - 내가 사용할 필요한 필드만
    - 싱글모델 내에서 외래 객체에 대한 참조 방법
      - OneToOne or ManyToOne (외래가 One) 인 경우
        1. Foreign Key 로 attribute 로 선언
        2. Value Object 로 생성후 attribute 로 선언
        3. Entity 로 생성후 relation 으로 연결하고, 필요한 필드만 복제
      - OneToMany (외래가 Many) 인 경우
        1. Entity 로 생성후 relation 으로 연결하고, 필요한 필드만 복제
  - 싱글모델들 간의 연동, Context Mapping
    - 상이한 컨텍스트 간의 매핑 방법
      - 도메인 이벤트를 기반한 연동
        - Pub/Sub
        - Sub 코드에서 Anti-corruption Layer 처리
          - 외래의 이벤트에서 내가 필요한 정보만을 이중화 저장
      - HATEOAS Link 를 기반한 연동
          - API 수준에서 참조한 데이터에 대한 _links 만 제공하고 UI Frontend 에서 Composition 하여 보여줌
  - 관련 질문
    - PetReserved.java 와 PetUpdated.java 가 왜 store 쪽 (받는 쪽) 에도 존재하나요?
      - store 도메인은 pet 도메인이 발행한 2개의 이벤트를 수신처리하기 위해 그것을 객체로 다시 받아들일 수 있어야 합니다 
        - (PolicyHandler.java 의 wheneverPetReserved_displayToTheShop 과 wheneverPetUpdated_updateItem). 
      - 이때 그 값을 Deserialize (역직렬화) 하여 받을 객체가 필요하기 때문에 양쪽에 존재해야 합니다.
- Step10 - Micro-Frontends
  - https://github.com/msa-school/ddd-petstore-frontend-pet-domain
  - https://github.com/msa-school/ddd-petstore-frontend-store-domain
- Step11 - Docker 빌드와 배포
  - https://youtu.be/4rIto9d0kpA
- 샘플링 결과
  - https://drive.google.com/drive/folders/1R4XDsTVEvxWWPubNxIBXnlzy1iYHNDJJ?usp=sharing
  - 2조:
    - https://labs.msaez.io/#/storming/nZJ2QhwVc4NlVJPbtTkZ8x9jclF2/9d4387474de013f46fa461339f69427d
  - 3조:
    - https://labs.msaez.io/#/storming/nZJ2QhwVc4NlVJPbtTkZ8x9jclF2/e8b65a327ce276160d7b38c759ef22b4
- 질의응답
  - Step1. 질문 하기 전에 아래의 미리 확인할 잦은 오류들을 먼저 읽어주세요
    - 잦은질문들
      - 나는 초급입니다 - 기본적인 질문들
        - 반영이 안됩니다
          - 관련 소스코드나 설정파일이 확실히 저장되었는지 확인
          - 스프링부트를 재기동 했는지 확인
            ```
            ctrl + c
            mvn spring-boot:run
            ```
        - 오류를 어떻게 자체 해결하나요?
          - at ... indent 가 들어간 맨 꼭데기 부분을 찾아서 구글에 검색
            - 예)  https://gunu91.tistory.com/21
            - Caused by: 에 있는 부분과 맨꼭데기 부분을 구글에 검색하다보면 해결됨
            - [object HTMLSpanElement]
    - JPA 관련
      - @Entity 애노테이션이 없을때 발생
        - Not a managed type: <클래스명>
          - 원인
            - Repository 의 선언에 사용된 Entity 의 선언에 @Entity 를 빼먹은 경우 발생
              - 예)
                ```java
                public interface TalentRepository extends JpaRepository<Talent, Long>
                ```
                ```
                Talent.java 에 
                @Entity 선언이 없이 class Talent 되어있으면 
                예
                @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: Not a managed type: Talent
                ```
              - 상속한 클래스들도 @Entity 선언이 필요합니다!
                - 예) Pet.java 를 상속한 Cat.java / Dog.java 에도 @Entity 가 선언부에 포함되어야 합니다.
      - @Id  선언은 한개의 필드에 대해서만 해야 하고, getter 에 해서도 안됩니다.
        - 예) getter 위에도 @Id 를 넣은 경우 entitymanager 에서 오류 발생함
          ```java
          @Entity
          public class Contract {
            @Id @GeneratedValue	
            Long id;
            
            @Id @GeneratedValue	
            public long getId() {	return id;	}
          }
          ```
        - setter/getter 의 파라미터/리턴 타입과 해당 필드 type 은 일치해야 합니다.
          - long <--> Long 
          - int <--> Integer
        - LazyLoading 옵션 주었을때 오동작 생길 수 있습니다. 
        - 생성되는 PATCH 메서드 때문에 SMART UI Anti-pattern 이 되는데, 이를 막으려면 어떻게 해야 하나요?
          - https://stackoverflow.com/questions/41345847/can-i-specifically-disable-patch-in-spring-data-rest-repository
            - Repeated column in mapping for entity: com.demo.dogwalker.domain.Order column: id (should be mapped with insert="false" update="false")
            ```java
            @AttributeOverride(name="id", column=@Column(name="menuId")) //embed 하면서 order.id 와 menuId.id가 겹치므로 회피해주는 
            ```
      - GitPod 클라우드 IDE (VS Code) 사용시
        - 설치 불필요, 웹 브라우저만 있으면 됨
        - 접속 후, 내부에서 Java Language Support 설치 필수
          - 설치하겠다는 자동 메시지 팝업시 꼭 "Install" 선택
            - 설치후 Window Refresh 하겠다는 메시지에서 꼭 "Refresh" 선택
        - 플러그인 설치:
          - https://chrome.google.com/webstore/detail/gitpod-always-ready-to-co/dodmmooeoklaejobgleioelladacbeki
          - 설치가 안되면, 해당 레포 주소창에서 gitpod.io/# 을 앞에 부착한 후, 접속
            - e.g. https://gitpod.io/#https://github.com/msa-school/ddd-petstore-level6-layered-spring-jpa
      - httpie 관련
        - 설치:  pip install httpie
        - GET
          ```
          http GET localhost:8080
          http get localhost:8080
          http localhost:8080
          http :8080
          ```
        - POST
          ```
          http POST :8080/orders productId=1
          http :8080/orders productId=1    # 보낼 값이 있으면 알아서 POST 로 인식
          http :8080/orders productId=1  address[city]="yongin"   # address 가 객체이고, 해당 객체의 세부 속성 city 에 값을 넣어야 하는경우
          http :8080/orders orderDetails[]="http://localhost:8080/items/1"  orderDetails[]="http://localhost:8080/items/2"   # orderDetails 에 하나 이상의 배열을 전송해야 하는 경우
          ```
        - DELETE
          ```
          http DELETE :8080/orders/1
          ```
        - PATCH
          ```
          http PATCH :8080/orders/1 qty=2   # 1번 주문의 qty 를 2로 변경
          ```
        - 예시
            ```
            # 회원등록
            http localhost:8080/customers id="park@naver.com" address[city]="yongin" address[country]="south korea"
            # 카트에 뽀삐담기
            http :8080/cartItems customer="http://localhost:8083/customers/park@naver.com" items[]="http://localhost:8080/pets/1"
            # 카트에 담긴 뽀삐확인
            http "http://localhost:8080/cartItems/2/items"
            ```
        - 오류
          - Connection 오류 
            ```
                http: error: ConnectionError: HTTPConnectionPool(host='localhost', port=8080): Max retries exceeded with url: / (Caused by NewConnectionError('<urllib3.connection.HTTPConnection object at 0x7fadff4d8df0>: Failed to establish a new connection: [Errno 111] Connection refused')) while doing a GET request to URL: http://localhost:8080/
                호출한 대상 서비스가 내려간 경우입니다. 다시 서비스를 올려주세요: 
                mvn spring-boot:run
            ```
      - Github 관련
        - Fork 가 안됩니다.
          - 한번 Fork한 Repo 는 그 Fork 의 원천이 같다면 모두 더이상 Fork 가 안됩니다. 
          - 원천의 Repo 를 Fork 한 프로젝트를 삭제하거나 다른 계정에서 Fork해야 합니다. 
          - 혹은, 전체 내용을 다운로드 받은 후 완전히 새로운 Github Repo 를 만들어야 합니다.
      - Maria/MySQL DB 관련
        - 접속이 안되는 경우
          - telnet 127.0.0.1 3306 으로 확인 시 접속이 원활한지 확인 필요
        - Character Set 을 한글로 하고 싶다면
          - https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=firstpcb&logNo=110189876753
        - Driver 가 존재하지 않는다는 오류가 난다면
          - Caused by: java.lang.IllegalStateException: Cannot load driver class: com.mysql.cj.jdbc.Driver
          - pom.xml 에 다음을 추가:
            ```
            <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <scope>runtime</scope>
            </dependency>
            ```
      - Docker 관련
        - Error response from daemon: Container XXXX is not running
          ```
          Error response from daemon: Container 0fa52a06c00aa0e9d12956406af0a2eb96f71035b8d041a217032ff734d5e6ac is not running다시 접속을 하니
          이런 에러가 나오면서 접속이 안됩니다
          gitpod /workspace/ddd-petstore-level6-layered-spring-jpa (main) $ docker run --name maria -d -p 3306:3306 -e MARIADB_ROOT_PASSWORD=admin mariadb:latest
          docker: Error response from daemon: Conflict. The container name "/maria" is already in use by container "0fa52a06c00aa0e9d12956406af0a2eb96f71035b8d041a217032ff734d5e6ac". You have to remove (or rename) that container to be able to reuse that name.
          이렇게 나오더라구요
          ```
        - 해당 컨테이너가 내려갔기 때문입니다. 다시 재시작해줘야 합니다:  
          - docker run --name maria mariadb:latest 
        - 다시 하려면 기존 이름이 중복되어있으므로 아래와 같이 삭제후에 해줘야 합니다:
          - docker rm maria
  - Step2. 잦은 질문에서 해결할 수 없다면, 
    -  현재 오류가 있는 자신의 소스코드를 커밋/푸시
      - VS Code 의 왼쪽 메인 탭에서 "Source Control" 클릭
      - 변경된 전체를 Commit Staging:  Changes 라인에 마우스를 대면 "+" 클릭
      - Commit: 상단에 간단한 커밋 메시지를 입력하고 "V" 클릭
      - Push: 하단의 오렌지색으로 "Sync Changes" 클릭
    - 해당 레포지토리의 url 과 함께 강사에게 오류에 대하여 질문
- 생각할 문제
  - type 필드를 잡아서 구분할 것인가? polymorphism 을 통해 구분할 것인가?
    - Polymorphism 을 적용한 도메인 클래스에 JPA를 적용했을때는 Discriminator 와 같은 상대적으로 복잡한 설정을 해야 하기 때문에, 해당 구분이 도메인 클래스 들의 행위가 달라지는 부분이 아닌 단순히 어떠한 데이터의 유형을 구분하는데는 그냥 type attribute 를 enum 등으로 잡아서 쓰는것이 좋을 수 있습니다. 
  - Aggregate 의 단위는 어떻게 잡는 것이 좋은가?
    - 기능 시나리오에 부합되는가?
      - Aggregate 는 트랜잭션이 벌어지는 모든 시나리오에 대하여 ACID 트랜잭션이 이루어져야 할 단위이므로 모든 시나리오가 먼저 검증되는지 확인해야 합니다. 
      - 예를 들어 회원과 주문이 있다면 회원 정보와 주문정보는 회원이 등록될때 주문이 한꺼번에 저장되는것이 아니기 때문에 별도의 AggregateRoot 들이 되어야 하고, 주문은 회원을 ManyToOne 관계로 연결하고 있어야 합니다. 반대로 회원이 주문을 OneToMany 로 필드 참조를 하고 있다면 이것이 Composition 처럼 작동하며, 회원 가입때 모든 주문 내역이 같이 저장되어야 한다는 의미이기 때문에 시나리오 성립이 안됩니다. 
    - 롱 트랜잭션인가? (peak 때 10~초 이상 소요되는 트랜잭션인가?)
      - 한번에 서버로 전송되어 ACID 트랜잭션이 묶이는 원자성을 가지는 데이터 묶음이 되므로, 이들은 천상 전체가 저장 혹은 취소되어야 하는 묶음이 됩니다. 따라서 불필요하게 많은 묶음으로 규정되면 성능의 저하를 야기합니다. 
      - 예를 들어 주문이 벌어질 때 관련한 배송처리, 결재처리, 이메일 발송등을 하나의 Aggregate 에서 모두 처리하려 든다면, 그 대기시간은 길어지며 이것이 멀티쓰레드로 동작해야 한다면 그 요청 컨텍스트를 기억하기 위해 과도한 메모리 소모가 벌어져서 서버 자원 소진으로 장애 요인 및 peak 상황에서 한번에 처리하지 않을 일이 한번에 하려시도하여 시간적으로는 자원이 남아있음에도 순간적으로 peak 에 도달하여 주문을 더이상 받지 못하는 한계가 발생합니다. 
      - 따라서 트랜잭션의 시간이 길어지면서 그 순간의 데이터 일치성이 요구되지 않는 선에서는 Eventual Consistency 로 분리 - Aggregate 를 나누고 그 둘간에는 이벤트 스토어를 경유하여 메시지를 주고 받아 쓰기 요청이 전파되도록 하는 방법 - 하는 것이 유리합니다.
  - Relation Annotation 들은 어디에 어떻게 잡는게 좋은가?
    - Aggregate 단위를 잡는 문제와 결부된 이슈
    - 하나의 단위로 저장되어야 할 Aggregate Root Class 에 Relation 을 메달아 준다.
    - 예) 회원과 주문
      - 하나의 회원에 여러 주문이 있으므로 "주문:회원 = n:1" 이다.
      - 주문이 될때는 어떤 회원이 한 주문인지에 대한 정보가 같이 한번에 서버로 전송되어야 하며, 원자적으로 ACID 하게 저장 (다 저장되거나 다 돌려놓거나) 되어야 하므로 주문->회원id 는 하나의 aggregate 가 되어야 한다. 
      - 회원은 주문과 관계없이 별도로 회원가입이 벌어지므로 회원도 Aggregate Root 이다.
      - 이때 다음의 2가지 정의가 가능하다:
        - case 1:   회원에 주문을 메달기
          ```java
          @Entity
          class 회원 {
            
            @OneToMany 
            List<주문> 주문들;
          }
          ```
        - case 2:   주문에 회원을 메달기
          ```java
          @Entity
          class 주문 {
            
            @ManyToOne 
            회원 주문자;
          }
          ```
        - 답:
          - case 2 로 정의해야만 회원과 주문이 따로 저장될 수 있고, 주문때 회원정보를 한번에 저장가능하다.
  - 참고자료
    - 강의자료
      [object HTMLSpanElement]
    - 객체지향 강의 채널 (14주 완성)
      - https://www.youtube.com/watch?v=nkRch3_Whvk&list=PLEr96Fo5umW-ee5vcoKKnCkS0nIn45_hY
    - DDD 관련 Facebook
      - https://www.facebook.com/groups/cloudswmoding