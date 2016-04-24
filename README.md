# PhoneBook

## for start server you must have:
 * 1) java -version 1.8
 * 2) javac -version 1.8
 * 3) apache-maven-3.3.9

## init database sql actions
 * create database phonebook;
 * create database phonebooktest;

## tables will be created automatically during start server via hibernate framework

## for start server do next command in terminal 'controller' folder :

 * 1) mvn clean;
 * 2) mvn package;
 * 3) java -jar targer/phonebook.war

 > or you can run with yours properties:
 * java -Dlardi.conf=/path/to/file.properties -jar targer/phonebook.war

## if you want use your own properties file you must create file with next form:

 * spring.datasource.url=jdbc:mysql://localhost:3306/[DATABASE_NAME]
 * spring.datasource.driverClassName=com.mysql.jdbc.Driver
 * spring.datasource.username=[USER_NAME]
 * spring.datasource.password=[PASSWORD]
