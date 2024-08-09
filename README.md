# Healthcare Management System

## Overview

This project is a Healthcare Management System developed as a microservices architecture using Java. The system is designed to manage various healthcare-related operations such as patient registration, appointment scheduling, medical records management, billing, and more. It serves as a practice project to demonstrate the implementation of microservices using modern Java technologies.

## Features

- **Patient Management**: Register and manage patient details.
- **Appointment Scheduling**: Schedule and manage appointments for patients with doctors.
- **Medical Records**: Store and retrieve patient medical records.
- **Billing and Invoicing**: Generate and manage billing information for healthcare services.
- **Doctor Management**: Manage doctor profiles and their availability.
- **Authentication and Authorization**: Secure the system with role-based access control.

## Microservices

The system is divided into several microservices, each responsible for a specific domain:

- **Patient Service**: Manages patient information.
- **Appointment Service**: Handles appointment scheduling.
- **Medical Record Service**: Manages patient medical records.
- **Billing Service**: Manages billing and invoicing.
- **Doctor Service**: Manages doctor profiles and availability.
- **Gateway Service**: Acts as an API gateway to route requests to appropriate services.
- **Authentication Service**: Manages user authentication and authorization.

## Technologies Used

- **Java 17**: Core programming language.
- **Spring Boot**: Framework for building microservices.
- **Spring Cloud**: For managing microservices, service discovery, and load balancing.
- **Eureka**: Service registry for microservices.
- **Feign Client**: For inter-service communication.
- **MySQL/PostgreSQL**: Relational database for persistent storage.
- **MongoDB**: NoSQL database for storing medical records.
- **Docker**: Containerization of microservices.
- **Kafka/RabbitMQ**: Message broker for event-driven communication between services.
- **Swagger/OpenAPI**: API documentation and testing.
- **JUnit & Mockito**: Testing frameworks for unit and integration testing.
- **Flyway/Liquibase**: Database migration tools.
- **Lombok**: Reducing boilerplate code.
- **Keycloak**: For authentication and authorization management.

## Installation

### Prerequisites

- Java 17
- Maven
- Docker
- Docker Compose

### Steps to Run the Project

1. **Clone the repository:**

    ```bash
    git clone https://github.com/arivan-amin/healthcare-management-system.git
    cd Healthcare-Management-System
    ```

2. **Build the project:**

    ```bash
    mvn clean install
    ```

3. **Run the services using Docker Compose:**

    ```bash
    docker-compose up -d
    ```

4. **Access the services:**

    - API Gateway: `http://localhost:8080`
    - Eureka Dashboard: `http://localhost:8761`
    - Swagger UI: `http://localhost:8080/swagger-ui.html`

## Testing

- Unit and integration tests are available for each microservice.
- Run the tests using Maven:

    ```bash
    mvn test
    ```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Contact

For any questions or inquiries, please contact:

- **Name:** Arivan Amin
- **Email:** arivanamin@gmail.com
