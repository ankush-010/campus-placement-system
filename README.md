# Campus Placement Management System

A student-level backend application developed using Java, Spring Boot and PostgreSQL to manage campus placement activities.

## Features

- Student management
- Company management
- Placement drive management
- Student eligibility checking
- Placement applications
- Application status management
- Student search and filtering
- User registration and login
- Password encryption using BCrypt
- Dashboard with placement statistics
- Input validation
- Global exception handling

## Technology Stack

- Java 25
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Spring Security Crypto
- BCrypt
- Gradle
- Postman
- IntelliJ IDEA

## Database

PostgreSQL is used as the database.

Database name:

campus_placement_db

Main tables:

- students
- companies
- placement_drives
- applications
- users

## API Endpoints

### Students

GET /api/students

GET /api/students/{id}

GET /api/students/search?branch=IT&cgpa=7.5

POST /api/students

PUT /api/students/{id}

DELETE /api/students/{id}

### Companies

GET /api/companies

POST /api/companies

### Placement Drives

GET /api/drives

POST /api/drives

### Applications

GET /api/applications

POST /api/applications/student/{studentId}/drive/{driveId}

PUT /api/applications/{applicationId}/status?status=SHORTLISTED

### Authentication

POST /api/auth/register

POST /api/auth/login

### Dashboard

GET /api/dashboard

## Example Workflow

1. Register a student.
2. Add a company.
3. Create a placement drive.
4. Check whether the student is eligible.
5. Student applies for the placement drive.
6. Placement status can be updated to:
    - APPLIED
    - SHORTLISTED
    - SELECTED
    - REJECTED
7. Dashboard displays placement statistics.

## Validation

The application validates important student fields such as:

- Name
- Email
- Phone
- Branch
- CGPA
- Graduation year
- Active backlogs

Invalid requests return appropriate error messages.

## Exception Handling

Global exception handling is implemented using `@RestControllerAdvice`.

Examples include:

- Student not found
- Company not found
- Placement drive not found
- Duplicate application
- Invalid login credentials
- Validation errors

## Project Structure

src/main/java/in/ankush/campusplacementsystem1

├── config

├── controller

├── dto

├── model

└── repository

## How to Run

### 1. Clone the repository

```bash
git clone <your-github-repository-url>