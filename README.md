**Axiom Test**

## Table of Contents

* [About the Project](#about-the-project)
* [Requirement](#Requirement)
* [Getting Started](#getting-started)
  * [Prerequisite](#prerequisite)
  * [Technologies Used](#technologies-used)
  * [Running](#running)
  * [Swagger Documentation](#swagger-documentation)
  * [Diagrams](#diagrams) 
* [Pending](#pending)
* [Improvement](#improvement)


## About The Project

It's to implement a Java application following the [Requirement](#Requirement)
below.

<!-- Requirement -->
## Requirement

At this URL: https://a511e938-a640-4868-939e-6eef06127ca1.mock.pstmn.io/handsets/list, you will
find a JSON file with a sample “Mobile handset” database. The data in this JSON is static, i.e. it
doesn’t get updated.

1  Create a Spring Boot application exposing a search API (GET /mobile/search?) that will allow the
caller to retrieve one or more mobile handset record based on the passed search criteria. The
criteria can be any field in the handset data along with any value. Examples:

-  /search?priceEur=200. Will return 10 devices.
-  /search?sim=eSim. Will return 18 devices.
-  /search?announceDate=1999&price=200. Will return 2 devices.


2. Consume the JSON API in the best way you see suitable.
3. Create Unit Test cases as you see suitable.
Provide a documentation and a high-level architecture of your application. You can do one or more
diagrams, as you see suitable, to describe your application functionality.


<!-- GETTING STARTED -->
## Getting Started

The project has only one endpoint that will allow you to search handsets.
The endpoint link is `http://localhost:8080/api/v1/mobile/search`

### Prerequisite

- Java 1.8 
- Maven

### Technologies Used
- Spring Webflux
- Java 1.8
- WebRequest




### Running 
To run the project please use  below maven commands

`mvn clean package
`
<br>
`mvn spring-boot:run
`
### Swagger documentation
Please use below link to access the api documentation
`http://localhost:8080/swagger-ui.html


### Design patterns 

The project is very simple therefore only few design patterns has been used and they are:
-  **Single Responsibility Principle**: all classes and methods has only one responsibility
-- **Dependency inversion**: Controller, Services  and Repositories are communicating through interfaces and abstractions 
-- **Liskov Substitution** : You can replace implementation easily without breaking 
`
### Diagrams 

The class diagrams of this project can be found on this  [link](classdiagrams.png)
<br>
The script of class diagrams can be found on this [link](classdiagrams.puml)
 
 `PlantUML has been used to design the class diagrams`

To edit the class-diagram in Intellij please install [PlantUML](https://plantuml.com) plugin.

## Pending
-  **Comments**: Not enough comments.
-  **SonarQube Testing**: The code has not been tested under SonarQube. Therefore the coverage and quality of code is unknown.
-  **Validation & Correct DataType** : I kept all dataType as String for simplicity, when data fetched from the 

## Improvement 
-  React Native Mobile: List can be shown on cross platform-mobile using react-native.
