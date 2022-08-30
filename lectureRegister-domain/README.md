20220831
controller searchAll GET 실행 시 에러 발생
2022-08-31 02:09:36.056 ERROR 26612 --- [nio-8081-exec-5] o.a.c.c.C.[.[.[/].[dispatcherServlet] : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.core.convert.ConversionFailedException: Failed to convert from type [java.lang.Object[]] to type [@org.springframework.data.jpa.repository.Query everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister] for value '{1, excel, excel class, 2, 5, 100000, null, null, COMPLETED}'; nested exception is org.springframework.core.convert.ConverterNotFoundException: No converter found capable of converting from type [java.lang.Long] to type [@org.springframework.data.jpa.repository.Query everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister]] with root cause

org.springframework.core.convert.ConverterNotFoundException: No converter found capable of converting from type [java.lang.Long] to type [@org.springframework.data.jpa.repository.Query everyoneslecture.lectureRegister.domain.LectureRegister.entity.LectureRegister]

20220830

1. zookeeper 실행
   D:\kafka\bin\windows>zookeeper-server-start.bat D:\kafka\config\zookeeper.properties

2. kafka 서버 실행
   D:\kafka\bin\windows>kafka-server-start.bat D:\kafka\config\server.properties

3. domain 터미널 실행

4. mvn spring-boot:run 실행
