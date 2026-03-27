# 🚀 Water Delivery Backend

[![Java](https://img.shields.io/badge/Java-17-blue)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Backend-green)]()
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue)]()
[![MySQL](https://img.shields.io/badge/MySQL-8-orange)]()
# Water Delivery System (Full Stack)

This project is a full-stack operational system built to demonstrate real-world backend engineering, frontend integration, and cloud deployment.

---

## 🔴 Live Demo

Frontend (Firebase Hosting):
https://water-delivery-491303-3757a.web.app/

Backend API (Cloud Run):
https://water-delivery-backend-66082310358.us-central1.run.app

Swagger Docs:
https://water-delivery-backend-66082310358.us-central1.run.app/swagger-ui.html

---

## 🧩 Architecture

Vue SPA (Firebase Hosting)
→ Spring Boot API (Cloud Run)
→ MySQL Database (Cloud SQL)

Designed for production-style deployment with environment-based configuration and real debugging workflows.

---

## ⚙️ Tech Stack

Backend:
- Java
- Spring Boot
- MySQL
- Cloud Run

Frontend:
- Vue.js (Vite)
- Firebase Hosting

Infrastructure:
- Google Cloud Platform (GCP)
- Cloud SQL
- Cloud Run

---

## 🚀 Features

- Employee management (CRUD)
- Live API integration
- Delete operations with real-time UI updates
- Cloud-deployed backend and frontend
- Production logging and debugging setup

---

## 🧠 Purpose

This project demonstrates the ability to design, build, debug, and deploy a complete system end-to-end.

Focus areas:
- Backend reliability
- API design
- Cloud deployment
- Full-stack integration

---

## 📌 Notes

This is not a toy project. It is structured to reflect real-world engineering practices including:
- Environment-based configuration
- Cloud database connectivity
- Production debugging via logs
- Separation of frontend and backend systems
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
