# REST API TaskMySQL

## Running TaskMySQL

##With Docker

## 1.Start MySQL database:

 docker run --name mysql-orders -e MYSQL_ROOT_PASSWORD=root
  -e MYSQL_USER=user
  -e MYSQL_PASSWORD=password
  -e MYSQL_DATABASE=mysql
  -p 3306:3306
  mySQL:5.7.8

## 2. Database configuration -  application.yml:
       spring:

         datasource:
           driver-class-name: com.mysql.cj.jdbc.Driver
           url: jdbc:mysql://localhost:3306/mysql
           username: user
           password: password

         jpa:
           open-in-view: false
           hibernate:
             ddl-auto: create
           show-sql: true
           properties:
             hibernate.dialect: org.hibernate.dialect.MySQL5Dialect
##3. Start application:

 command: gradlew bootrun

##4. Run tests:


 Database for testsing will be running at testcontainers:mysql after

 command : gradlew test

   You could use Plagine Gradle too.








