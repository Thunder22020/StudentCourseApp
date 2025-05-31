# Student-Course Management System

## На данный момент находится в разработке

Java Spring Boot-приложение для управления студентами и курсами с использованием REST API. Поддерживается двусторонняя связь `ManyToMany` между сущностями `Student` и `Course`, а также используется DTO-слой для предотвращения рекурсивной сериализации.

## Технологии

- Java 17+
- Spring Boot 3
- Spring Data JPA
- PostgreSQL (в зависимости от настроек)
- Lombok
- Maven

## Структура проекта

```
src
└── main
    └── java
        └── com.app.studentcourse
            ├── controller
            ├── service
            ├── repository
            ├── dto
            └── entity
```