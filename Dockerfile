# 用和你项目一致的JDK版本，比如JDK11/17都可以
FROM openjdk:17-jdk-slim

# 复制你打包好的jar包到容器里，这里要写你target里的jar全名
COPY target/xianglian-web-0.0.1-SNAPSHOT.jar app.jar

# 暴露SpringBoot默认的8080端口
EXPOSE 8080

# 启动命令
ENTRYPOINT ["java", "-jar", "app.jar"]