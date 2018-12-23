# Bookity!

Bookity! is a lightweight small online library. You can search the books and see information of them.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them
You need to have these things to install and run bookity-web-app.

```
Java 1.8.0_192 +
Apache Maven 3.6.0 +
MySql Database Server 8.0.13 + 
```

### Installing

First you'll need to run the scripts in database.sql to create the tables in your MySql schema.
Then you should change the following attributes in application.properties.

```
spring.datasource.url
spring.datasource.username
spring.datasource.password
```
If you want to use another database server instead of MySql you need to change the attributes started with `spring.jpa` in application.properties file. It's under your responsibility.

The next step you should run the command below to create an executable war file in bookity-web-app folder.
```
mvn clean install
```

Change the location and file the war file in target folder and put application.properties file in the same folder and run the following command below.

```
java -jar bookity-web-app-0.1.0.war
```

It starts and run Bookity on the port you provided in application.properties file.

###Configuration

Some fields in application.properties file are configurable. You can change the fields below for following purposes.

For log files path and name you need to configure these fields:
```
logging.path
logging.file 
```

For the records to show in the table per page you need the change this field:
```
bookity.book.pagesize
```

For email address which sends welcome mail change these fields:

```
spring.mail.host
spring.mail.port
spring.mail.username
spring.mail.password
spring.mail.protocol
spring.mail.properties.mail.smtp.auth
spring.mail.properties.mail.smtp.starttls.enable
```


## Authors

* **Oguzhan Dogan** - *Initial work* - [ogzhndgn](https://github.com/ogzhndgn) dogan_oguzhan@hotmail.com