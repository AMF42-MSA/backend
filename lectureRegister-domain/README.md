1. zookeeper 실행
  D:\kafka\bin\windows>zookeeper-server-start.bat D:\kafka\config\zookeeper.properties

2. kafka 서버 실행
  D:\kafka\bin\windows>kafka-server-start.bat D:\kafka\config\server.properties

3. domain 터미널 실행

4. mvn spring-boot:run 실행

5. update
  http PUT localhost:8081/lectureRegisters/RegistLect lectId=2342, lectName="CLOUD", lectRegistStatus="ING"
