# mbrd-Assignment:
This is a brief guide to help understand the flow of this assignment which involves what the application is all about, what tools are needed to set up this application and the
entire flow of the application. 

## Overview
* [About Project](#about-project)
  * [Introduction](#Introduction-project)
  * [Skills and Technologies used](#Skills-and-technology)
* [Tools Installed](#Tools-installed)
* [Project Setup](#Project-setup)
* [Project Flow](#Project-Flow)

## About Project
### Introduction:
This is a springboot-microservice backend application to securely store data in a file format and allow the user to read and update when required. The backend supports storing the data in both CSV and XML file format.
The application contains 2 microservices projects which are independently built & deployable.

### Skills And Technologies Used:

First Header | Second Header
------------ | -------------
OS: | Windows(64bit)
Programming Language: | Java7, Java8
Web service: | Restful Webservice
Database: | Files: CSV, XML
Enterprise API: | Collection Framework
Web Application Framework: | Springboot, Micro-services
AMQP: |  RabbitMQ
Logging Tool: | slf4j
Build And Integration Tool: | Maven
Testing Tool: | Junit
Repository Tool: | Git
IDE: | Eclipse
Other Tools: | Postman


## Tools Installed:
- Eclipse & Embedded Maven
- PostMan
- Rabbit MQ - https://www.rabbitmq.com/download.html
- Erlang- http://www.erlang.org/downloads

## Project Setup:
- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the right project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application
- Start rabbitmq server
- You are all Set


## Project Flow:
- There are 2 microservices created namely: front-end microservice and data-storage microservice.
### MICROSERVICE1: FRONT-END MICOSERVICE
- Front-end microservice is a consumer-facing service which accepts request from the user.
- It accepts the data in JSON format within the request body.
- This service exposes 4 REST endpoints to retrieve all data, retrieve data of particular user, insert data and update already existing data.
- The service also accepts an input 'fileType' as a parameter.
- The service first does the validation of the data entered by the user-input like: age is not negative, Name has more than 2 characters etc
- Once the validation of input data is done then depending upon the fileType (CSV or XML) send from the user-input, data is stored/updated to the corresponding csv/xml file    using AMQP- RabbitMq.
- However, to retrieve the entire existing data, service retrieves all data present in both the files(CSV and XML). 
- Not limited to this much, the service is also capable of retrieving the data for a particular user using http mode of communication.
- Thus front-end-service is basically accepting the data from the user and validating it and passing over this validated data to the service 2- data-storage service through 
  asynchronous data transfer mechanism called Advanced Message Queuing Protocol via RabbitMq.

### MICROSERVICE2: DATA-STORAGE MICOSERVICE
- This microservice simply accepts the data passed on by microservice 1- Front-end-service over RabbitMq and depending upon the fileType parameter it stores/updates the data into the CSV/XML file.
- This service also provides the abitiy to retrieve the requested data for a particular user or to retrieve all the data.

### JUNIT Testing:
- There are testcases made to test these above functionalities.
