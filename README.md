# Getting Started

This is a sample application showcasing the use of Hexagonal Architecture with Domain-Driven Design (DDD), Kafka, Spring Boot, and PostgreSQL. The application is designed to demonstrate the separation of concerns and the modularity of different layers.

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
   - [Clone the repository](#clone-the-repository)
   - [How to start](#how-to-start)
      - [Documentation in `Swagger`](#documentation-in-swagger)
      - [Access Kafka Dashboard](#access-kafka-dashboard)

## Introduction

This application serves as a template for building scalable and maintainable Java applications using modern technologies. It employs Hexagonal Architecture to separate the core business logic from the external components, making it easier to adapt to changes in the future.

## Prerequisites

Before you begin, ensure you have the following installed:

- Java 21 or later
- Apache Gradle
- Docker

## Setup

1. Clone the repository:
     ```
     git clone https://github.com/mateusrfiuza/orders.git
      cd orders
     ```
2. How to start:
To start it you can run the command below:
     ```
      ./gradlew clean build bootRun
     ```


#### Documentation in `Swagger`:
1. Open your browser
2. Type in URL bar `http://127.0.0.1:8080/swagger-ui.html`


#### Access Kafka Dashboard:
1. Open your browser
2. Type in URL bar `http://127.0.0.1:9021/`