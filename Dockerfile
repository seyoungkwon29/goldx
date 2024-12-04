# 빌드 단계 (멀티스테이지 빌드)
FROM eclipse-temurin:21-jdk AS builder

# Gradle wrapper 및 관련 파일 복사
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .
COPY src/ src/

# Gradle wrapper 실행 권한 부여
RUN chmod +x ./gradlew

# Gradle 빌드 실행 (자바 애플리케이션의 jar 파일을 빌드)
RUN ./gradlew bootJar --no-daemon

# 런타임 단계 (최소화된 이미지)
FROM eclipse-temurin:21-jre

# 빌드한 jar 파일을 복사
COPY --from=builder build/libs/*.jar app.jar

# 포트 8443을 외부에 노출
EXPOSE 8443

# 자바 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]
