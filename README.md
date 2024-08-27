# Healthcare Management System

## Overview

This project is a Healthcare Management System developed as a microservices architecture using Java.
The system is designed to manage various healthcare-related operations such as patient registration,
appointment scheduling, medical records management, billing, and more. It serves as a practice
project to demonstrate the implementation of microservices using modern Java technologies.

## Microservices

The system is divided into several microservices, each responsible for a specific domain:

- **API Gateway**: Serves as the single entry point for all client requests, routing them to the
  appropriate microservices. It handles request aggregation, load balancing, and provides a
  centralized point for managing cross-cutting concerns such as authentication and logging.
- **Appointment Service**: Manages scheduling and rescheduling of patient appointments. It interacts
  with the Doctor and Patient services to ensure availability and updates the system with the latest
  appointment data.
- **Billing Service**: Handles the generation and management of invoices and payments. It integrates
  with various services to collect billing information and ensures accurate billing for patient
  services and treatments.
- **Core Service**: Provides foundational services and shared functionality used across other
  microservices. This could include utility functions, common data access layers, or basic service
  management features.
- **Discovery Service**: Implements service discovery mechanisms, allowing microservices to find and
  communicate with each other dynamically. It maintains a registry of available services and their
  locations.
- **Doctor Service**: Manages information related to doctors, including their profiles, specialties,
  schedules, and availability. It supports operations related to doctor management and integrates
  with other services for scheduling and patient care.
- **Inventory Service**: Tracks and manages the inventory of medical supplies, equipment, and
  pharmaceuticals. It provides real-time updates on stock levels and handles inventory-related
  operations such as ordering and restocking.
- **Lab Service**: Handles laboratory tests and results. It manages the submission, tracking, and
  reporting of lab test orders, as well as the retrieval of test results for patients and doctors.
- **Medical Record Service**: Manages patient medical records, including history, diagnoses,
  treatment plans, and other health-related information. It ensures secure access and updates to
  patient records as required by various services.
- **Notification Service**: Sends notifications and alerts to users, such as appointment reminders,
  billing notifications, or system updates. It handles different communication channels like email,
  SMS, or in-app notifications.
- **Patient Service**: Manages patient information, including personal details, medical history, and
  contact information. It supports patient registration, updates, and interactions with other
  services such as appointments and billing.
- **Pharmacy Service**: Manages prescriptions and pharmaceutical inventory. It processes
  prescription orders, tracks medication stock, and provides information about available drugs and
  their interactions.
- **Prescription Service**: Handles the creation, management, and fulfillment of medical
  prescriptions. It integrates with the Doctor, Pharmacy, and Patient services to ensure accurate
  prescription processing and medication delivery.
- **Radiology Service**: Manages radiological imaging and reports. It handles requests for imaging
  procedures, stores image data, and provides reports and results to doctors and patients.
- **Reporting Service**: Generates and manages reports related to various aspects of the system,
  including operational metrics, financial summaries, and patient statistics. It provides insights
  and data visualizations for analysis and decision-making.
- **Security Service**: Ensures the security of the system by managing authentication,
  authorization, and encryption. It handles user access controls, secure communication, and
  protection of sensitive data.
- **Staff Service**: Manages information related to hospital or clinic staff, including roles,
  schedules, and personal details. It supports operations related to staff management and
  integration with other services for scheduling and notifications.
- **Surgery Service**: Handles scheduling, management, and documentation of surgical procedures. It
  integrates with other services to coordinate pre-surgery preparations, manage surgical resources,
  and track post-operative care.

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

## Installation

### Prerequisites

- Java 21
- Maven
- Docker
- Docker Compose

### Steps to Run the Project

1. **Clone the repository:**

    ```bash
    git clone https://github.com/arivan-amin/Healthcare-Management-Microservices.git
    cd Healthcare-Management-Microservices
    ```

2. **Build the project, and it will load the images to your local docker repository using JIB:**

    ```bash
    mvn clean package
    ```

3. **Set Eureka username and password environment variables and docker host ip:**
    - on Linux: add the below variables to your .bashrc file and reload or reboot
    ```bash
    export EUREKA_USER=admin
    export EUREKA_PASSWORD=admin
    export DOCKER_HOST_IP=172.17.0.1
    ```
    - on Windows: run the below commands
    ```bash
    set EUREKA_USER=admin
    set EUREKA_PASSWORD=admin
    set DOCKER_HOST_IP=host.docker.internal
    ```
4. **Run the services using Docker Compose:**

    ```bash
    docker compose up -d
    ```

5. **Access the services:**

    - API Gateway: `http://localhost:8080`
    - Eureka Dashboard: `http://localhost:8080/eureka/web`
    - Swagger UI: `http://localhost:8080/swagger-ui.html`

## Testing

- Unit and integration tests are available for each microservice.
- Run the tests using Maven:

    ```bash
    mvn test
    ```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements
or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Contact

For any questions or inquiries, please contact:

- **Name:** Arivan Amin
- **Email:** arivanamin@gmail.com
