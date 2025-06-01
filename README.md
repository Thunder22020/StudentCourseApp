# Student-Course Management API

This is a Spring Boot application for managing students and courses with a bidirectional `ManyToMany` relationship. It provides RESTful endpoints for creating and retrieving students and courses.

## Features

- Create, update, delete students and courses
- Assign students to courses and vice versa
- Use of DTOs to prevent infinite recursion
- REST API (JSON)
- PostgreSQL

## Technologies Used

- Java 17+
- Spring Boot 3
- Spring Data JPA
- Lombok
- Maven
- H2 Database (for development)

## Project Structure

```
src
└── main
├── controller // REST controllers
├── dto // DTO classes for request/response
├── entity // JPA entities: Student, Course
├── repository // Spring Data JPA repositories
└── service // Business logic
```

## Running the Project

1. Clone the repository:
```bash
   git clone https://github.com/Thunder22020/StudentCourseApp.git
```
## Example requests

- *POST: localhost:8080/students* - сохраняет студента в базу данных с возможностью сразу указать существующие курсы
```
{
    "name": "student1",
    "age": 21,
    "courses": []
}
```
Response:
```
201 CREATED
{
    "name": "student1",
    "age": 21,
    "id": 1,
    "courses": []
}
```

- *POST: localhost:8080/courses*  - сохраняет курс в базу данных с возможностью сразу указать существующих студентов
```
{
    "name": "Math",
    "students": [
        {
            "name": "student1",
            "age": 21,
            "id": 1
        }
    ]
}
```
Response:
```
201 CREATED
{
    "name": "Math",
    "id": 1,
    "students": [
        {
            "name": "student1",
            "age": 21,
            "id": 1
        }
    ]
}
```

- *GET: localhost:8080/students* - возвращает всех студентов

Response:
```
200 OK
[
    {
        "name": "student1",
        "age": 21,
        "id": 1,
        "courses": [
            {
                "name": "Math",
                "id": 1
            }
        ]
    }
]
```

- *DELETE: localhost:8080/students/{id}* - удаляет студента по id

Request: `localhost:8080/students/1`

Response: `200 OK`