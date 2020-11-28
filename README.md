# CS2001 - Lab Worksheet 6

**MODULE DETAILS**

* Lab Title: Spring Boot and REST
* Lab Coordinator: Nadine Aburumman
* Lab sheet: 6 (Week 9 2020/21)

Spring Boot Lab 6 on creating the GenderDecoder REST API's using JPA annotation and a MySQL database.

### GenderDecoder API's implemented

* **/analyze** - For users to submit a text for analysis. It should return the result of analysis. This endpoint
does not save the analysed ad to the database. GET endpoint
* **/texts** - POST endpoint: Save an ad to the database
* **/texts/{id}** - DELETE endpoint: Delete a job text by id.

These API's are implemented in com.example.genderdecoder.controller.ProcessedTextController

### MySQL database creation:

Open a MySQL shell and setup the database as follows:

Step | Command | Comment
------------ | ------------- | -------------
**1** | `\sql` | Switches to SQL processing mode
**2** | `\connect root@localhost`  | Connects the shell to MySQL server with user=root, host=localhost. You will need to enter the root password afterexecuting this statement.
**3** | `create database genderdecoder_app;` | SQL statement for creating a new database called “genderdecoder_app”
**4** | `create user 'springuser'@'%' identified by 'ThePassword';` | Creates user springuser; % matches any hostname; authenticated by its password. You may want to change ‘ThePassword’ to a stronger password.
**5** | `grant all on genderdecoder_app.* to 'springuser'@'%';` | Gives all privileges to the new user on the newly created database

### Credits
Brunel CS2001 teaching team
