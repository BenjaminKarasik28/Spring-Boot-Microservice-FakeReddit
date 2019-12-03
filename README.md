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
We worked collaboratively to ensure that all project deliverables would be met. Starting with the planning phase, we developed a list of user stories to help guide the development process and created a deliverables timeline. 

A few challenging implementation aspects of this project:

Configuration of most DevOps tools/services (such as Jenkins / ELK) were very difficult to set up. Even with the most minor of changes there was the potential to crash the whole application.


## System Architecture
![erd](https://github.com/BenjaminKarasik28/Spring-Boot-Microservice-FakeReddit/blob/master/System%20Architecture%201.png)


## ERD 

![ERD](https://github.com/BenjaminKarasik28/Spring-Boot-Microservice-FakeReddit/blob/master/ERD%202.png)

## Swagger
We utilized Swagger to document all of our endpoints. See screenshot below:

![erd](https://github.com/BenjaminKarasik28/Spring-Boot-Microservice-FakeReddit/blob/master/Swagger.png)

## Javadoc

![erd](https://github.com/BenjaminKarasik28/Spring-Boot-Microservice-FakeReddit/blob/master/Javadoc.png)

## Kibana
There is ELK logging set up on our application. We implemented custom monitoring on the service layer so that we are able to more closely observe any potential issues. See screenshot below:
