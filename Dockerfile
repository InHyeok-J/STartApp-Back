FROM openjdk:17-oracle

WORKDIR app

ARG JAV_FILE=./build/libs/*.jar

COPY ${JAV_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Seoul" ,"app.jar"]