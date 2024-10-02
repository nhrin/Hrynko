# To-Do List Application

## Overview
This is a simple To-Do List application built with Spring Boot, Spring Security, and an H2 database. The application allows users to register, log in, and manage tasks in their to-do lists. Users can create, view, update, and delete tasks, as well as attach files to tasks.

## Features
- User registration
- User authorization (login)
- Create, read, update, and delete tasks
- Attach files to tasks
- In-memory database (H2) for development

## Technologies Used
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- H2 Database
- Lombok
- JUnit and Mockito for testing

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven

### Installation
1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/todo-list-app.git
   cd todo-list-app
   ```

2. **Build the application**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080`.

### API Endpoints

#### User Registration
- **Endpoint:** `POST /api/users/register`
- **Request Body:**
    ```json
    {
        "username": "new_user",
        "password": "password123"
    }
    ```
- **Response:** Success or error message

#### User Login
- **Endpoint:** `POST /api/users/login`
- **Request Body:**
    ```json
    {
        "username": "existing_user",
        "password": "password123"
    }
    ```
- **Response:** Success or error message

#### Task Management

- **Create Task**
  - **Endpoint:** `POST /api/tasks`
  - **Request Body:**
      ```json
      {
          "description": "New Task",
          "completed": false,
          "userId": 1
      }
      ```
  - **Response:** Created task object

- **Get All Tasks**
  - **Endpoint:** `GET /api/tasks`
  - **Response:** List of tasks

- **Update Task**
  - **Endpoint:** `PUT /api/tasks/{id}`
  - **Request Body:**
      ```json
      {
          "description": "Updated Task",
          "completed": true
      }
      ```
  - **Response:** Updated task object

- **Delete Task**
  - **Endpoint:** `DELETE /api/tasks/{id}`
  - **Response:** Success or error message

## Testing
To run the unit tests for the application, execute the following command:

```bash
mvn test
```