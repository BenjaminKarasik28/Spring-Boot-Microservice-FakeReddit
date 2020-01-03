# Spring-Boot-Microservice-FakeReddit

## Table of Contents
* [Technologies Used](#technologies-used)
* [Dependencies and Installation](#dependencies-and-installation)
* [How to Run](#how-to-run)
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

## How to Run
- Clone the project on your local machine
- Open it in your preferred IDE (We used Intellij Ultimate-Edition && Eclipse)
- Turn on Postgres (if downloaded form site) / Run Postgres (if downloaded with Brew)
- Run "rabbitmq-server" in Terminal
- Run each Microservice Spring project by clicking the run button ( Run Eureka-Server first followed by API-Gateway and then the rest in any order)
- Go to www.http://localhost:8761 to check if all servers are up; all but Eureka Client should be listed
- Open Postman and run endpoints to test functionality (Endpoints listed in SwaggerDocs) 
- Bonus: Try using our tool to send yourself an email: Create an account with your real email, create a post, comment on that post! (Certain functionality requires authentication. If you recieve a 401 HTTP response message, this means for this HTTP request you must use the JWT-token returned on either login or signup. Copy the token, go to the HTTP function you want to run, for example: create a post, click on authentication, for "Type", select bearer and then paste the token 

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

![erd](https://github.com/BenjaminKarasik28/Spring-Boot-Microservice-FakeReddit/blob/master/Jenkins.png)
