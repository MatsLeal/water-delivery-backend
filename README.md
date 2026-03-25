# 🚀 Water Delivery Backend

[![Java](https://img.shields.io/badge/Java-17-blue)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Backend-green)]()
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue)]()
[![MySQL](https://img.shields.io/badge/MySQL-8-orange)]()

---

## 🧠 Overview

A production-style backend system built with **Spring Boot** to manage employees and delivery operations.

This project demonstrates **clean architecture, validation, Dockerized infrastructure, and API best practices**.

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL (Docker)
- Adminer (DB GUI)
- Lombok
- Spring Validation
- Spring Boot Actuator

---

## 🏗️ Architecture

```
Controller → Service → Repository → Database
```

```
src/main/java/com/coreforge/waterdeliverybackend
├── controller
├── dto
├── exception
├── model
├── repository
└── service
```

---

## 🧰 Prerequisites

- Java 17
- Docker
- Maven (or use ./mvnw)

---

## ▶️ Setup & Run

### 1. Clone

```
git clone https://github.com/YOUR_USERNAME/water-delivery-backend.git
cd water-delivery-backend
```

### 2. Start DB

```
docker compose up -d
```

### 3. Run app

```
./mvnw spring-boot:run
```

---

## 🌐 API

### Employees
- POST /employees
- GET /employees
- GET /employees/rud/{rud}

### Deliveries
- POST /deliveries
- GET /deliveries/employee/{employeeId}
- PUT /deliveries/{id}

---

## ❤️ Health

- /health
- /actuator/health
- /actuator/info

---

## 🧪 Example

```
curl -X POST http://localhost:8080/employees -H "Content-Type: application/json" -d '{"name":"Mats","rud":"550002","area":"Ops"}'
```

---

## ❌ Error Handling

- Centralized exception handling
- Validation errors (400)
- Not found errors (404)

---

## 🚀 Features

- Clean layered architecture
- DTO validation
- Global exception handling
- Dockerized DB
- Health monitoring

---

## 🔮 Future Improvements

- Swagger / OpenAPI
- JWT Authentication
- Pagination
- CI/CD

---

## 👤 Author

Mats Johann Leal Rangel  
LinkedIn: https://www.linkedin.com/in/mats-johann-leal-rangel-b86114190

---

⭐ Designed to reflect real-world backend engineering practices.
