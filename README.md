# Human Resource Management Application

## Overview

Welcome to the Human Resource Management Application! This project serves as the final project for Tekup University. The application is designed to facilitate human resource management tasks within an organization. It provides features for managing employee data, roles, permissions, and other HR-related functions.

## Features

- **Employee Management**: Create, update, and delete employee records.
- **Role Management**: Define various roles within the organization and assign permissions.
- **Permission Management**: Manage permissions associated with different roles.
- **Authentication and Authorization**: Secure access to the application using authentication and enforce authorization rules based on user roles.
- **Database Integration**: Utilizes MariaDB as the backend database for storing application data.
- **Java Version**: Developed using JDK 17.

## Setup

### Prerequisites

Before running the application, ensure you have the following installed:

- JDK 17: [Download and install JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
- MariaDB: [Download and install MariaDB](https://mariadb.org/download/).
- Maven: [Download and install Maven](https://maven.apache.org/download.cgi).

### Configuration

1. Clone the repository to your local machine:

    ```
    git clone https://github.com/MoetazAissaoui/PDEV-TEKUP.git
    ```

2. Configure the MariaDB database:
   
    - Create a new database for the application (e.g., `hr_management`).
    - Update the `application.properties` file with the database connection details, including URL, username, and password.

3. Build the application:

    ```
    mvn clean install
    ```

4. Run the application:

    ```
    mvn spring-boot:run
    ```

5. Access the application:

    Once the application is running, access it through your web browser at `http://localhost:8080`.

## Usage

- **Swagger Documentation**: Visualize API documentation using Swagger at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).