# Spring-Boot-Microservice-FakeReddit

## Table of Contents
* [Technologies Used](#technologies-used)
* [Dependencies and Installation](#dependencies-and-installation)
* [User Stories](#user-stories)
* [Project Deliverables and Timeline](#project-deliverables-and-timeline)
* [General Approach](#general-approach)
* [System Architecture](#system-architecture)
* [ERD](#erd)


## Technologies Used
- Java / IntelliJ
- Git / GitHub - to host our code for version control and a shared working repository
- Pivotal Tracker - to write user stories and keep track of the technical requirements
- Postman for testing endpoints without a UI
- Slack for communication between developers

## Dependencies and Installation
- Maven
- Docker

Refer to the dependencies in the pom.xml file for the following:
- Spring Boot
- Hibernate
- Postgres

## User Stories
Link:   https://www.pivotaltracker.com/n/projects/2416891

## Project Deliverables and Timeline
**11/11**
- User stories
- ERD

**11/12 and 11/13**
- User-api microservice
- User profile and roles
- Add security

**11/14**
- Post-api microservice
- Comment-api microservice

**11/15**
- Interservice communication to handle cascading data removal

**11/16**
- Integrate backend with frontend application
- Testing
- Readme

## General Approach
We worked collaboratively to ensure that all project deliverables would be met. Starting with the planning phase, we developed a list of user stories to help guide the development process, discussed the system architecture design and created a deliverables timeline. 

A few challenging implementation aspects of this project:

**Security integration with API gateway** - We had spent some time brainstorming the best way to handle security and initially integrated it within our user-api. However, after discussions in class we decided that implementing security within API gateway was a better approach since every request would be filtered through API gateway and this would help to keep our code more DRY. Integration was challenging as we kept running into authentication issues. 

**Interservice communication** - Since our application is separated into three microservices (User, Post, Comment). We had to figure out how to cascade the appropriate data updates across these services. For example, if a user removes a post then the associated comments with that posts should also be removed. After researching and finding a few options that would allow us to accomplish this such as the @feign approach or utilizing messaging such as RabbitMQ, we decided to go with the RestTemplate approach as it appeared to be a more streamlined method.

## System Architecture
![erd](https://github.com/BenjaminKarasik28/Spring-Boot-Microservice-FakeReddit/blob/master/System%20Architecture%201.png)


## ERD 

![ERD](https://github.com/BenjaminKarasik28/Spring-Boot-Microservice-FakeReddit/blob/master/ERD%201.png)

