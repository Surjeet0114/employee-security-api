# Employee Security API

A Spring Boot REST API for employee management with **Spring Security, JWT authentication, role-based authorization, refresh tokens, validation, and PostgreSQL**.

This project focuses specifically on implementing application-level security in a Spring Boot REST API.

## 🚀 Features

- Employee CRUD operations
- User registration
- User login
- BCrypt password hashing
- JWT-based authentication
- Access token generation
- Refresh token generation
- Refresh token validation
- Refresh token revocation/logout
- Role-based authorization
- `ADMIN` and `USER` roles
- Method-level security
- Request validation
- Global exception handling
- PostgreSQL database
- Spring Data JPA
- Stateless authentication
- DTO-based API design

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Spring Boot | Backend framework |
| Spring Security | Authentication & authorization |
| JWT | Token-based authentication |
| Spring Data JPA | Database access |
| Hibernate | ORM |
| PostgreSQL | Relational database |
| Maven | Build & dependency management |
| BCrypt | Password hashing |
| Jakarta Validation | Request validation |

## 🏗️ Project Structure

```text
employee-security-api
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── surjeet
│   │   │           └── employee_security_api
│   │   │               ├── config
│   │   │               ├── controller
│   │   │               ├── dto
│   │   │               ├── entity
│   │   │               ├── exception
│   │   │               ├── repository
│   │   │               ├── security
│   │   │               └── service
│   │   │                   └── impl
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## 🔐 Authentication Flow

The application uses JWT-based stateless authentication.

```text
Register
   ↓
Login
   ↓
Username + Password
   ↓
Spring Security Authentication
   ↓
JWT Access Token + Refresh Token
   ↓
Client sends Access Token
   ↓
JWT Authentication Filter
   ↓
SecurityContext
   ↓
Protected API
```

## 🔄 Refresh Token Flow

When the access token expires, the client can use the refresh token to obtain a new access token.

```text
Access Token Expired
        ↓
Send Refresh Token
        ↓
Validate Refresh Token
        ↓
Check Expiry
        ↓
Check Revocation
        ↓
Generate New Access Token
```

## 🚪 Logout Flow

Logout revokes the refresh token.

```text
Logout Request
      ↓
Find Refresh Token
      ↓
Revoke Token
      ↓
Refresh Token Cannot Be Used Again
```

## 👥 Roles

The application supports two roles:

### USER

A normal authenticated user.

Depending on endpoint authorization, a `USER` can access permitted employee operations but cannot perform administrator-only operations.

### ADMIN

An administrator with access to administrator-protected employee operations.

Role-based authorization is implemented using Spring Security.

## 📡 API Overview

### Authentication

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Authenticate user and generate tokens |
| POST | `/api/auth/refresh` | Generate a new access token |
| POST | `/api/auth/logout` | Revoke refresh token |

### Employee

The Employee API provides CRUD operations protected by Spring Security.

Typical operations include:

| Method | Purpose |
|---|---|
| GET | Retrieve employee data |
| POST | Create employee |
| PUT | Update employee |
| DELETE | Delete employee |

Authorization depends on the configured user role.

## 🗄️ Database

The project uses PostgreSQL.

Create the database before starting the application:

```sql
CREATE DATABASE springbootdb;
```

Database configuration is provided through environment variables.

## ⚙️ Configuration

Sensitive credentials are intentionally not stored directly in `application.properties`.

Example configuration:

```properties
spring.application.name=employee-security-api

server.port=9090

spring.datasource.url=jdbc:postgresql://localhost:5432/springbootdb
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.jpa.open-in-view=false

jwt.secret=${JWT_SECRET}
jwt.expiration=${JWT_EXPIRATION:3600000}
```

### Required Environment Variables

```text
DB_USERNAME
DB_PASSWORD
JWT_SECRET
JWT_EXPIRATION
```

Example:

```text
DB_USERNAME=postgres
DB_PASSWORD=your_password
JWT_SECRET=your_long_random_secret
JWT_EXPIRATION=3600000
```

Do not commit real credentials or JWT secrets to GitHub.

## ▶️ Running the Application

### 1. Clone the repository

```bash
git clone <repository-url>
cd employee-security-api
```

### 2. Configure PostgreSQL

Make sure PostgreSQL is running and the required database exists.

### 3. Configure environment variables

Set:

```text
DB_USERNAME
DB_PASSWORD
JWT_SECRET
JWT_EXPIRATION
```

### 4. Run tests

Windows:

```powershell
.\mvnw.cmd clean test
```

Linux/macOS:

```bash
./mvnw clean test
```

### 5. Start the application

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Linux/macOS:

```bash
./mvnw spring-boot:run
```

The application runs on:

```text
http://localhost:9090
```

## 🧪 Testing

The API can be tested using tools such as:

- Postman
- IntelliJ HTTP Client
- curl

Recommended testing flow:

```text
1. Register user
2. Login
3. Copy access token
4. Send Bearer token with protected requests
5. Test USER authorization
6. Test ADMIN authorization
7. Refresh access token
8. Logout
9. Verify refresh token is revoked
```

For authenticated requests, send:

```http
Authorization: Bearer <access-token>
```

## 🔒 Security Implementation

The project demonstrates several Spring Security concepts:

- Authentication
- Authorization
- Password hashing
- JWT authentication
- JWT request filtering
- Stateless sessions
- Role-based access control
- Method-level security
- Token expiration
- Refresh token management
- Refresh token revocation

## 📚 Learning Objectives

This project was built to understand how security can be implemented in a Spring Boot REST API.

The main concepts demonstrated are:

```text
Spring Security
      ↓
Authentication
      ↓
Authorization
      ↓
Password Hashing
      ↓
JWT
      ↓
JWT Filter
      ↓
Role-Based Access
      ↓
Refresh Tokens
      ↓
Token Revocation
```

## 🔮 Possible Future Improvements

The current project is considered complete for its **Spring Security/JWT learning scope**.

Possible future improvements include:

- More integration tests
- More comprehensive unit tests
- Improved security exception handling
- API documentation with Swagger/OpenAPI
- Docker support
- Docker Compose
- Redis-based refresh token storage
- Email verification
- Password reset
- Account locking
- Rate limiting
- Audit logging

These are optional extensions and are not required for the current project scope.

## 📌 Project Status

**Status: Complete for Security/JWT stage**

Implemented:

- Employee REST API
- PostgreSQL persistence
- Spring Security
- JWT authentication
- Role-based authorization
- Refresh tokens
- Token revocation
- Validation
- Exception handling

The project is intentionally focused on **Spring Boot security fundamentals** rather than microservices or distributed-system technologies.

## 👨‍💻 Author

**Surjeet Kumar**

B.Tech CSE (AI/ML)

## 📄 License

This project is intended for learning and educational purposes.