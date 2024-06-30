# Spring Boot CSV Uploader API

## Overview

This Spring Boot application provides RESTful APIs for uploading and processing CSV files. The application uses Spring Data JPA for database interactions, and exposes Actuator endpoints for monitoring and management.

## Table of Contents

- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
  - [Upload CSV](#upload-csv)
  - [Health Check](#health-check)
  - [Actuator Endpoints](#actuator-endpoints)
- [Accessing H2 Database](#accessing-h2-database)
- [Running the Application](#running-the-application)

## Getting Started

### Prerequisites

- JDK 8 or higher
- Maven
- Git

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/allasassis/hacken-test.git
    cd hacken-test
    ```

2. Build the application:
    ```sh
    ./mvnw clean install
    ```

3. Run the application:
    ```sh
    ./mvnw spring-boot:run
    ```

## API Endpoints

### Upload CSV

- **URL:** `/api/upload`
- **Method:** `POST`
- **Description:** Uploads a CSV file and processes the data.

#### Request

- **Content-Type:** `multipart/form-data`
- **Parameters:**
  - `file`: The CSV file to upload.

#### Example

```sh
curl -X POST -F 'file=@path/to/yourfile.csv' http://localhost:8080/api/upload
```

### Search Data

- **URL:** `/api/search`
- **Method:** `GET`
- **Description:** Searches the CSV data based on a query string.

#### Request

- **Parameters:**
    - `query` (String): The search query.

#### Example

```sh
curl -X GET 'http://localhost:8080/api/search?query=searchTerm'
```

### Health Check

- **URL:** `/actuator/health`
- **Method:** `GET`
- **Description:** Checks the health status of the application.

#### Example

```sh
curl -X GET http://localhost:8080/actuator/health
```

### Actuator Endpoints

Spring Boot Actuator provides several built-in endpoints for monitoring and managing your application.

- **URL:** `/actuator`
- **Method:** `GET`
- **Description:** Lists all available Actuator endpoints.

#### Example

```sh
curl -X GET http://localhost:8080/actuator
```

Some useful Actuator endpoints:
- `/actuator/info` - Provides application information.
- `/actuator/metrics` - Exposes metrics for monitoring.
- `/actuator/env` - Shows environment properties.

## Accessing H2 Database

The application uses an H2 in-memory database for development purposes.

### H2 Console

- **URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (leave blank)

To access the H2 console:
1. Open your browser and go to `http://localhost:8080/h2-console`.
2. Enter the JDBC URL, username, and password as specified above.
3. Click "Connect".

## Running the Application

To run the application, use the following command:

```sh
./mvnw spring-boot:run
```

## Configuration

You can customize the application settings by modifying the `application.properties` or `application.yml` file located in the `src/main/resources` directory.

### Example `application.properties`

```properties
# Server settings
server.port=8080

# H2 Database settings
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb

# Actuator settings
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
```