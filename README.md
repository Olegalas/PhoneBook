# PhoneBook

# init database sql actions
create database phonebook;

# tables will be created automatically during start server
# via hibernate framework

# TODO add init data script(users, contacts) for test data

mvn clean;
mvn package;

java -jar
