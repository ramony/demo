FROM openjdk:19-jdk-alpine
#创作者名字
MAINTAINER raybyte

ARG JAR_FILE
#添加jar包并重命名，此处为jar，并不会解压
COPY ${JAR_FILE} /usr/local/user.jar

ENTRYPOINT ["java","-jar","/usr/local/user.jar","--spring.profiles.active=k8s"]