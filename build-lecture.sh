cd lecture-domain
mvn clean package -DskipTests

cd ..
docker-compose -f docker-compose-lecture.yml build
docker-compose -f docker-compose-lecture.yml up