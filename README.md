# Healthcare Management System

## Overview

This project is a **Healthcare Management System** developed as a **Microservices Architecture**
using **Java**.
The system is designed to manage various healthcare-related operations such as patient registration,
appointment scheduling, medical records management, billing, and more. It serves as a practice
project to demonstrate the implementation of microservices using modern **Java** technologies.

## Technologies Used

- **Java 21**: Core programming language.
- **Spring Boot**: Framework for building microservices.
- **Spring Cloud**: For managing microservices, service discovery, and load balancing.
- **Eureka**: Service registry for microservices.
- **Feign Client**: For inter-service communication.
- **MySQL**: Relational database for persistent storage.
- **MongoDB**: NoSQL database for storing data.
- **Docker**: Containerization of microservices.
- **Kafka**: Message broker for event-driven communication between services.
- **Swagger/OpenAPI**: API documentation and testing.
- **JUnit & Mockito**: Testing frameworks for unit and integration testing.
- **Flyway**: Database migration tools.
- **Lombok**: Reducing boilerplate code.
- **Keycloak**: For authentication and authorization management.
- **Grafana**: For observability and monitoring services through dashboards.
- **loki**: For centralized logging.
- **tempo**: For distributed tracing.

## Grafana Monitoring Screenshots

### Services monitoring dashboard main section

![image](https://raw.githubusercontent.com/arivan-amin/Healthcare-Management-Microservices/master/Docs/Grafana/Grafana-Dashboard-1.png)

### Graphs for total requests response time and response time per endpoint

![image](https://raw.githubusercontent.com/arivan-amin/Healthcare-Management-Microservices/master/Docs/Grafana/Grafana-Dashboard-2.png)

### Graphs for total calls made to Jpa repository

![image](https://raw.githubusercontent.com/arivan-amin/Healthcare-Management-Microservices/master/Docs/Grafana/Grafana-Dashboard-4.png)

### Graphs for percentage of HTTP request status codes, 2xx, 3xx, 4xx, and 5xx

![image](https://raw.githubusercontent.com/arivan-amin/Healthcare-Management-Microservices/master/Docs/Grafana/Grafana-Dashboard-6.png)

### Graphs for graph on .95 and .99 percentile histogram to identify bottlenecks

![image](https://raw.githubusercontent.com/arivan-amin/Healthcare-Management-Microservices/master/Docs/Grafana/Grafana-Dashboard-7.png)

### Graphs for JVM performance, threads, GC, logs and IO

![image](https://raw.githubusercontent.com/arivan-amin/Healthcare-Management-Microservices/master/Docs/Grafana/Grafana-Dashboard-8.png)

### Graphs for JVM memory usage, heap, non heap, and more

![image](https://raw.githubusercontent.com/arivan-amin/Healthcare-Management-Microservices/master/Docs/Grafana/Grafana-Dashboard-9.png)

### Graphs for Hikari connection pool performance

![image](https://raw.githubusercontent.com/arivan-amin/Healthcare-Management-Microservices/master/Docs/Grafana/Grafana-Dashboard-10.png)

### Graphs for Tomcat sessions, send and receive and threads

![image](https://raw.githubusercontent.com/arivan-amin/Healthcare-Management-Microservices/master/Docs/Grafana/Grafana-Dashboard-11.png)

### Graphs for log events and their volume per minute

![image](https://raw.githubusercontent.com/arivan-amin/Healthcare-Management-Microservices/master/Docs/Grafana/Grafana-Dashboard-12.png)

## Installation

### Prerequisites

- **Java 21**
- **Maven**
- **Docker**
- **Docker Compose**

### Steps to Run the Project

1. ### Clone the repository:

    ```bash
    git clone https://github.com/arivan-amin/Healthcare-Management-Microservices.git
    cd Healthcare-Management-Microservices
    ```

2. ### Build and install the Core Module because all modules depend on it:

    ```bash
    cd Core
    mvn clean install
    cd ..
    ```
3. ### Build the project, and it will load the images to your local docker repository using JIB:

    ```bash
    mvn clean package
    ```

4. ### Set Eureka username and password environment variables and docker host ip:
    - on **Linux**: add the below variables to your **.bashrc** file and reload or reboot
    ```bash
    export EUREKA_USER=admin
    export EUREKA_PASSWORD=admin
    ```
    - on **Windows**: run the below commands
    ```bash
    set EUREKA_USER=admin
    set EUREKA_PASSWORD=admin
    ```
5. ### Run the services using Docker Compose:
    ```bash
    docker compose up -d
    ```

6. ### Access the services:
    - **API Gateway:** `http://localhost:8080`
    - **Eureka Dashboard:** `http://localhost:8080/eureka/web`
    - **Swagger UI:** `http://localhost:8080/swagger-ui.html`
    - **Grafana:** `http://localhost:3000/dashboards`

7. ### Accessing the Grafana Dashboards for monitoring Spring Boot and MySQL
    - Open your browser and navigate to **Grafana** at: http://localhost:3000/dashboards
    - In the **Grafana** dashboards page, click on the **New** icon on the top right side and select
      **Import**.
    - Choose both json file located at: **project-root/docker/grafana/**
    - Complete the import process for both files.
    - Now you should have 2 dashboards to monitor Spring Boot, MySQL.

## Testing
- Unit and integration tests are available for each microservice.
- Run the tests using Maven:

    ```bash
    mvn test
    ```

## Microservices
The system is divided into several microservices, each responsible for a specific domain:

- **API Gateway**: Serves as the single entry point for all client requests, routing them to the
  appropriate microservices. It handles request aggregation, load balancing, and provides a
  centralized point for managing cross-cutting concerns such as authentication and logging.
- **Appointment Service**: Manages scheduling and rescheduling of patient appointments. It interacts
  with the Doctor and Patient services to ensure availability and updates the system with the latest
  appointment data.
- **Audit Service**: Provides centralized logging and auditing for all actions within the system. It
  records critical events, such as user activities and service interactions, to ensure transparency
  and compliance.
- **Core Service**: Provides foundational services and shared functionality used across other
  microservices. This could include utility functions, common data access layers, or basic service
  management features.
- **Discovery Service**: Implements service discovery mechanisms, allowing microservices to find and
  communicate with each other dynamically. It maintains a registry of available services and their
  locations.
- **Doctor Service**: Manages information related to doctors, including their profiles, specialties,
  schedules, and availability. It supports operations related to doctor management and integrates
  with other services for scheduling and patient care.
- **Notification Service**: Sends notifications and alerts to users, such as appointment reminders,
  billing notifications, or system updates. It handles different communication channels like email,
  SMS, or in-app notifications.
- **Patient Service**: Manages patient information, including personal details, medical history, and
  contact information. It supports patient registration, updates, and interactions with other
  services such as appointments and billing.
- **Security Service**: Ensures the security of the system by managing authentication,
  authorization, and encryption. It handles user access controls, secure communication, and
  protection of sensitive data.

## Contributing
**Contributions are welcome!** Please fork the repository and submit a pull request for any
improvements
or bug fixes.

## License
This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for more
details.

## Contact
For any questions or inquiries, please contact:
- **Name: Arivan Amin**
- **Email: arivanamin@gmail.com**
