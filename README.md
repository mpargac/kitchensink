# Kitchensink project

## Overview
The Kitchensink project is a web application that was migrated from a legacy architecture based on Jakarta EE 10, JSF, H2 database, and Red Hat JBoss EAP 8.0 to a modern stack using Java 21, Spring Boot, and MongoDB. The application includes a web interface for member registration and management, a REST API for CRUD operations, and utilizes Mapstruct for converting entity objects to DTOs.

## Technology Stack
- **Java 21:** The latest version of Java, bringing improvements in performance, features, and stability.
- **Spring Boot 3.3.4:** Used to simplify the configuration and setup of the application. It provides a more flexible, scalable, and maintainable architecture.
- **Spring Web MVC:** Utilized to create a robust and structured web application following the Model-View-Controller pattern.
- **Thymeleaf:** A server-side template engine to build dynamic web pages. Used to generate the user interface for the member registration form and display the list of members.
- **MongoDB:** A NoSQL database replacing H2. MongoDB is more suited for modern, cloud-native applications due to its scalability and flexible schema.
- **Mapstruct:** A code generator that automates the conversion between entities and DTOs. DTOs are used by both the REST API and the web application to simplify data transfer and decouple domain logic from UI and API layers.
- **Maven:** A build automation tool used to manage dependencies, compile the project, and streamline the build lifecycle.
- **Docker:** Used to run MongoDB in a containerized environment,

## Features
### Web MVC application
Simple web page using Spring Web MVC and Thymeleaf. Main functionality are:
- **New member registration:** Allows new members to register in the application and store.
- **List all members:** List all members stored in the MongoDB database.

### REST API: 
Exposes CRUD operations for member management.
- **Create:** Add new member.
- **Read:** Retrieve details of all or individual members.
- **Update:** Modify existing member details.
- **Delete:** Remove members from the system.
DTO Conversion using Mapstruct: Ensures clean separation between the database layer (entities) and the API/web layers (DTOs), simplifying data handling.

## Prerequisites
Ensure that you have the following software installed:

- **Java 21 SDK:** [Download Java 21](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html)
- **Maven:** [Maven Installation Guide](https://maven.apache.org/install.html)
- **MongoDB:** Ensure MongoDB is running locally or on a cloud service.
- **Docker:** Install Docker for running MongoDB in a container.

## Running MongoDB with Docker
You can run MongoDB as a Docker container using the following steps:

1. Pull the official MongoDB Docker image:
```
docker pull mongo:latest
```

2. Run the MongoDB container:
```
docker run -d -p 27017:27017 --name=kitchensink-db mongo:latest
```

This command will start a MongoDB instance, mapping port 27017 on your local machine to the same port in the Docker container. You can also use existed file from project:
```
/src/main/resources/run-mongo.bat
```

3. Confirm MongoDB is running by checking the container status:
```
docker ps
```
Once MongoDB is running, the application will connect to this instance using the default MongoDB URI: (mongodb://localhost:27017/kitchensink-db).

## Getting Started

1. Clone the repository https://github.com/mpargac/kitchensink.git
2. Build and Run the Application
Use Maven to build the application:
```
mvn clean install
```
Run the Spring Boot application:
```
mvn spring-boot:run
```
3. Access the Application
Once the application is running, it can be accessed at:
http://localhost:8081/kitchensink

## Web Features
Web application for listing all members and registering new members can be accessed at:
http://localhost:8081/kitchensink

## REST API
The application exposes a REST API for managing members: http://localhost:8081/kitchensink/rest/members 
The following endpoints are available for CRUD operations:

| HTTP method | Endpoint                       | Description                       |
| ----------- | ------------------------------ | --------------------------------- |
| GET         | /kitchensink/rest/members      | Retrieves all members             |
| GET         | /kitchensink/rest/members/{id} | Retrieves a specific member by ID |
| POST        | /kitchensink/rest/members      | Creates a new member              |
| DELETE      | /kitchensink/rest/members/{id} | Deletes a member                  |

Note: The API utilizes DTOs to encapsulate member data, which are automatically mapped from entity objects using Mapstruct.

## Configuration
The application can be customized by modifying properties in src/main/resources/application.properties

## Tests
There are unit tests implemented using JUnit Jupiter and Mockito
