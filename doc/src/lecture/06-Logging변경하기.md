# Logging
- 2022-07-29: 초안 작성
- Spring Boot의 기본 로깅

## 1. 추가 변경이 필요한 내용(7/29)

1. logger를 서비스 단위로 변경하기
   - 동시에 여러 서비스가 실행되어도 하나의 로깅으로 추적할 방법은
2. 실시간 로깅 레벨 변경(서비스 단위로)
3. 로깅 rolling 설정하기(일정 크기...)
4. 저널(서비스 입/출력)내역 별도 로깅하기

## 2. 간단 변경한 내용 정리

1. 로그 라인 추가하기
   - LECTURE의 Application.yaml 파일 중에서
   - 컬러 부분은 아직 확인을 못했음
   ```yaml
   logging:
   level:
      org.hibernate.type: trace
      org.springframework.cloud: debug
      lecturemgt: debug
   pattern.level: "user:%X{user} %5p"
   #출처: https://luvstudy.tistory.com/133 [파란하늘의 지식창고:티스토리]
   pattern.console: "%d{HH:mm:ss.SSS} [%t] %-5level %logger{-36}:%L - %msg%n"
   #https://www.bswen.com/2019/07/springboot-Show-logging-linenumber-in-springboot-applications.html
   ```
2. 
