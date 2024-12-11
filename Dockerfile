FROM openjdk:21-jdk

# Docker 컨테이너 내에서 작업 디렉토리를 설정. 이후 모든 명령어는 이 디렉토리 내에서 실행
WORKDIR /spring-boot

# SNAPSHOT.jar 파일을 컨테이너의 /spring-boot 에 app.jar 파일로 복사
COPY build/libs/goldx-0.0.1-SNAPSHOT.jar app.jar

# Docker 컨테이너가 시작되면 java -jar /spring-boot/app.jar 명령어가 실행
# 이 명령어가 Spring Boot 애플리케이션을 실행
ENTRYPOINT ["java", "-jar", "/spring-boot/app.jar"]