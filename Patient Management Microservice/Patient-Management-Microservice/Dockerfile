FROM openjdk:21-ea-17-jdk


ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} patientservice.jar

ENTRYPOINT ["java","-jar","/patientservice.jar"]

EXPOSE 4000