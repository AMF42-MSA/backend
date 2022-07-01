# Lecture(강의)

## 1. Running in local development environment
1. run
    ```
    mvn spring-boot:run
    ```
2. Packaging and Running in docker environment
    ```
    mvn package -B
    docker build -t username/class:v1 .
    docker run username/class:v1
    ```
3. Push images and running in Kubernetes

```
docker login 
# in case of docker hub, enter your username and password

docker push username/class:v1
```

Edit the deployment.yaml under the /kubernetes directory:
```
    spec:
      containers:
        - name: class
          image: username/class:latest   # change this image name
          ports:
            - containerPort: 8080

```

Apply the yaml to the Kubernetes:
```
kubectl apply -f kubernetes/deployment.yml
```

See the pod status:
```
kubectl get pods -l app=class
```

If you have no problem, you can connect to the service by opening a proxy between your local and the kubernetes by using this command:
```
# new terminal
kubectl port-forward deploy/class 8080:8080

# another terminal
http localhost:8080
```

If you have any problem on running the pod, you can find the reason by hitting this:
```
kubectl logs -l app=class
```

Following problems may be occurred:

1. ImgPullBackOff:  Kubernetes failed to pull the image with the image name you've specified at the deployment.yaml. Please check your image name and ensure you have pushed the image properly.
1. CrashLoopBackOff: The spring application is not running properly. If you didn't provide the kafka installation on the kubernetes, the application may crash. Please install kafka firstly:

https://labs.msaez.io/#/courses/cna-full/full-course-cna/ops-utility

## 코드 테스트 방법

1. http로 테스트
   - pip install httpie
    ```
    http :8081/lectures title="MSA" minEnrollment=1 maxEnrollment=10 status="OPENED" categoryId[id]="1"
    -- 결과 조회
    http GET localhost:8081/lectures/1  

    http :8082/enrollments customerId="myinno" classId[id]="1" status="ENROLLED"

    http :8083/categories title="MSA_catalog"

    ```
    wsl 
    cd /tmp/docker-desktop-root/mnt/host/d/APP/git-amf3/backend#



## 2. 테이블 확인

### 처음 생성된 테이블
```bash
    create table class_table (
       id bigint not null,
        category_id_id bigint,
        max_enrollment integer,
        min_enrollment integer,
        status varchar(255),
        title varchar(255),
        primary key (id)
    )
```

  ```java
  @Entity
  @Table(name = "Class_table")
  @Data
  public class Clazz {

      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private Long id;

      private String title;

      private Integer minEnrollment;

      private Integer maxEnrollment;

      @Enumerated(EnumType.STRING)
      private Status status;

      @Embedded
      @AttributeOverride(
          name = "id",
          column = @Column(name = "categoryIdId", nullable = true)
      )
      private CategoryId categoryId;

  }
  ```
  ```java
    @Embeddable
    @Data
    public class CategoryId {
      private Long id;

    }
  ```
    
1. 변경 내용

	```java
	@Embeddable
	@Data
	public class CategoryId {

		private Long id;

		// 만약 필드가 2개 이상이면 테이블에 어찌 생성될까
		private String categoryName;
	}
	```
2. 변경후 테이블에
	```bash
	create table class_table (
	   id bigint not null,
		category_name varchar(255),
		category_id_id bigint,
		max_enrollment integer,
		min_enrollment integer,
		status varchar(255),
		title varchar(255),
		primary key (id)
	)
	```
3. 리스트로 변경후
	```java
	private CategoryId categoryId;
	<변경후>
	private List<CategoryId> categoryId;
	```
4. 결과(무시함)	
	```bash
	create table class_table (
	   id bigint not null,
		max_enrollment integer,
		min_enrollment integer,
		status varchar(255),
		title varchar(255),
		primary key (id)
	)
	```
5. String 필드 하나로 변경하고 List처리

	```java
	@Embeddable
	@Data
	public class CategoryId {
		private String categoryName;
	}
	```

6. 결과(무시함- 이전과 동일)	
	```bash
	create table class_table (
	   id bigint not null,
		max_enrollment integer,
		min_enrollment integer,
		status varchar(255),
		title varchar(255),
		primary key (id)
	)
	```
  
 ## 3. 2일차(7/01) 코딩
 1. class --> lecture  ==> 완료
 2. 강의 분류를 ReadModel로 만들자