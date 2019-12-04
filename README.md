# Spring-Boot-Microservice-FakeReddit

## Table of Contents
* [Technologies Used](#technologies-used)
* [Dependencies and Installation](#dependencies-and-installation)
* [User Stories](#user-stories)
* [Project Deliverables and Timeline](#project-deliverables-and-timeline)
* [General Approach](#general-approach)
* [System Architecture](#system-architecture)
* [ERD](#erd)
* [Swagger](#swagger)
* [Javadoc](#javadoc)
* [Kibana](#kibana)
* [Jenkins](#jenkins)

## Technologies Used
- Java / IntelliJ
- Spring-Boot
- Postgres - to manage relational databases
- Git / GitHub - to host our code for version control and a shared working repository
- Pivotal Tracker - to write user stories and keep track of the technical requirements
- Postman for testing endpoints without a UI
- Slack for communication between developers

## Dependencies and Installation
- Maven
- Docker
- Spring-Boot
- RabbitMQ

Refer to the dependencies in the pom.xml file for the following:
- Spring Boot
- Hibernate
- Postgres

## User Stories
Link:   https://www.pivotaltracker.com/n/projects/2416891

## Project Deliverables and Timeline
**11/21**
- User stories
- RabbitMQ
- CRUD

**11/22 & 11/25**
- RabbitMQ
- Swagger
- Exception handling and input validation

**11/26 & 11/27**
- Jenkins
- Unit tests

**11/29**
- Unit tests
- ELK

**11/30**
- Unit tests
- Integration tests

**12/1**
- Unit tests

**12/2**
- Unit tests

**12/3**
- Testing of applications

## General Approach
We worked collaboratively to ensure that all project deliverables would be met. Starting with the planning phase, we developed a list of user stories to help guide the development process and created a deliverables timeline. Pivotal tracker and the deliverables timeline helped with prioritization so that we could allocate a larger portion of time to the more complex features. Our approach was to try to spend the initial few days of the project tackling RabbitMQ and handling all the configs needed for Jenkins, ELK and Swagger so that we can spend the latter half of the project working on testing.

A few challenging implementation aspects of this project:

Generally, configuration of all the DevOps tools/services required for this project (such as Jenkins / Swagger / ELK) were difficult to set up. We spent a lot of time researching how to troubleshoot various issues. 

Testing API Gateway was also challenging since there were many complex methods.


## System Architecture
![erd](https://github.com/BenjaminKarasik28/Spring-Boot-Microservice-FakeReddit/blob/master/System%20Architecture%20updated.png)


## ERD 

![ERD](https://github.com/BenjaminKarasik28/Spring-Boot-Microservice-FakeReddit/blob/master/ERD%202.png)

## Swagger
We utilized Swagger to document all of our endpoints. See screenshot below for user-api:

![erd](https://github.com/BenjaminKarasik28/Spring-Boot-Microservice-FakeReddit/blob/master/Swagger.png)

## Javadoc

![erd](https://github.com/BenjaminKarasik28/Spring-Boot-Microservice-FakeReddit/blob/master/Javadoc.png)

## Kibana
There is ELK logging set up on our application. We implemented custom monitoring on the service layer so that we are able to more closely observe any potential issues. See screenshot below for user-api:

![erd](https://github.com/BenjaminKarasik28/Spring-Boot-Microservice-FakeReddit/blob/master/Kibana.png)

## Jenkins
We were able to accomplish over 80% test of application collectively through unit and integration tests. We monitored the test coverage using Jacoco. See screenshot below: 
