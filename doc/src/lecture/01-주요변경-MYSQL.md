# Lecture-mysql



## 1.mysql 연동
2022-07-24일

### 1.1 환경설정 변경 내용
1. lectured의 Application.yaml변경
   - Local테스트 없이 직접 docker기반 테스트만 함
   - 전체 내용은 소스 참조
   - 소스 수정은 별도 하지 않음
        ```yaml
        # 추가된 내용
        spring.datasource:
        url: jdbc:mysql://mysql/lecture
        username: user
        password: pwd

        spring.datasource.hikari.initializationFailTimeout: 60000

        spring.jpa.generate-ddl: true
        spring.jpa.hibernate.ddl-auto: update
        spring.jpa.properties:
        hibernate:
            show_sql: true
            format_sql: true
        ```
2. Docker-compose 파일 변경
   - 다른 사람과 테스트 독립성을 위하여 별도 compose 파일 생성
   - docker-compose-lecture.yml(mysql 관련 추가)
        ```yaml
        # $ mysql -uroot -h127.0.0.1 -p
        mysql:
            image: mysql:5.7
            mem_limit: 350m
            ports:
            - "3306:3306"
            environment:
            - MYSQL_ROOT_PASSWORD=rootpwd
            - MYSQL_DATABASE=lecture
            - MYSQL_USER=user
            - MYSQL_PASSWORD=pwd
            healthcheck:
            test: ["CMD", "mysqladmin" ,"ping", "-uuser", "-ppwd", "-h", "localhost"]
            interval: 10s
            timeout: 5s
            retries: 10
        ```
    - lecture에 depend-on추가
        ```yaml
            depends_on:
            - kafka, mysql
        ```
### 1.2 테스트
1. 실행
    ```bash
    cd lecture
    mvn package

    cd  ..  #root 디렉토리로 이동

    docker-compose -f docker-compose-lecture.yml build
    docker-compose -f docker-compose-lecture.yml up


    # swagger로 테스트
    ```
2. mysql 결과 확인
   - DBeaver 다운로드 및 설치
     - 접속정보 ![](images/01-connection.png)
        ```bash
        # docker-desktop에서 CLI로 접근
        SELECT id, category_id_id, max_enrollment, min_enrollment, status, title, version
            FROM `lecture-db`.lecture_table;

        1	11	20	10	CLOSED	string
        2	11	20	10	CLOSED	string
        3	11	20	10	CLOSED	string
        4	11	20	10	CLOSED	string
        5	11	20	10	CLOSED	string
        6	11	20	10	CLOSED	string
        7	11	20	10	CLOSED	string
        8	11	20	10	CLOSED	string
        ```
## 2. Multi DB 테스트
- 하나의 MySQL에 2개 이상의 database만들기
- 참조한 내용: https://namsieon.com/24

### 2.1 shell파일 생성
- docker-compose-mysql 파일과 동일한 위치에 "initialize_mysql_multiple_databases.sh"생성
    ```sh
    # initialize_mysql_multiple_databases.sh

    if [ -n "$MYSQL_MULTIPLE_DATABASES" ]; then
    for dbname in $(echo $MYSQL_MULTIPLE_DATABASES | tr ',' ' '); do
        echo $dbname: $MYSQL_USER
        mysql -u root -p$MYSQL_ROOT_PASSWORD <<-EOSQL
            CREATE DATABASE $dbname;
            GRANT ALL PRIVILEGES ON $dbname.* TO 'user'@'%';
            EOSQL
    done
    fi
    ```

### 2.2 docker-compose 수정 내용
- DB명에 '-'사용시 오류가 발생함
- 'MYSQL_MULTIPLE_DATABASES' 추가
- 'volumes' 부분 추가: sh수행
  - 잘못 생성되었으면 vloumn을 삭제하고 다시 수행
- volumn 추가
    ```yaml
    version: '2'
    volumes:
      mysql-volume: {}
    services:
    # $ mysql -uroot -h127.0.0.1 -p
    mysql:
        image: mysql:5.7
        mem_limit: 350m
        ports:
        - "3306:3306"
        environment:
        - MYSQL_ROOT_PASSWORD=rootpwd
        - MYSQL_MULTIPLE_DATABASES=lecture,member
        - MYSQL_USER=user
        - MYSQL_PASSWORD=pwd
        volumes:
        - ./initialize_mysql_multiple_databases.sh:/docker-entrypoint-initdb.d/initialize_mysql_multiple_databases.sh
        - mysql-volume:/var/lib/mysql
          healthcheck:
          test: ["CMD", "mysqladmin" ,"ping", "-uuser", "-ppwd", "-h", "localhost"]
          interval: 10s
          timeout: 5s
          retries: 10
    ```

## 3. MYSQL 한글입력을 위한 character set 설정

- command 부분 추가
    ```yml
    version: '2'
    volumes:
    mysql-volume: {}
    services:
    # $ mysql -uroot -h127.0.0.1 -p
    mysql:
        image: mysql:5.7
        mem_limit: 350m
        ports:
        - "3306:3306"
        environment:
        - MYSQL_ROOT_PASSWORD=rootpwd
        - MYSQL_MULTIPLE_DATABASES=lecture,member
        - MYSQL_USER=user
        - MYSQL_PASSWORD=pwd
        volumes:
        - ./initialize_mysql_multiple_databases.sh:/docker-entrypoint-initdb.d/initialize_mysql_multiple_databases.sh
        - mysql-volume:/var/lib/mysql
        command:
        - --character-set-server=utf8
        - --collation-server=utf8_general_ci
        healthcheck:
          test: ["CMD", "mysqladmin" ,"ping", "-uuser", "-ppwd", "-h", "localhost"]
          interval: 10s
          timeout: 5s
          retries: 10
    ```
