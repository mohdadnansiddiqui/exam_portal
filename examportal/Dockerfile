# Step 1: Base image — Docker apna Java lega
FROM openjdk:17-jdk-slim

# Step 2: Container ke andar ka working folder
WORKDIR D:\project\backend\examportal\app\examportal

# Step 3: Jar file copy karo container ke andar
COPY target/*.jar examportal.jar

# Step 4: Port expose karo (Spring Boot default 8080)
EXPOSE 8080

# Step 5: App chalu karo jab container chale
ENTRYPOINT ["java", "-jar", "examportal.jar"]
