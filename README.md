# Smart Greenhouse Management System

## 📌 Description
This project is a microservices-based web application for managing connected greenhouses.
It monitors environmental conditions and automatically controls greenhouse equipment.

The application follows a Spring Boot microservices architecture with Angular front-end,
ensuring scalability, fault tolerance, and centralized configuration.

---

## 🧱 Architecture
- Microservices architecture (Spring Boot)
- Service Discovery with Eureka Server
- API Gateway
- Centralized Configuration Server
- Synchronous REST communication
- Asynchronous communication using RabbitMQ
- Angular Front-end
- Docker containerization
- Kubernetes deployment (bonus)

---

## 🛠 Technologies
- Java 17
- Spring Boot
- Spring Cloud
- Angular
- RabbitMQ
- Docker & Docker Compose
- Kubernetes (optional)

---

## 📂 Repository Structure
- backend/: Spring Boot microservices
- frontend/: Angular application
- docker/: Docker and Docker Compose files
- kubernetes/: Kubernetes manifests
- docs/: Architecture and documentation

---

## 🚧 Project Status
In progress – Architecture and core microservices setup.
## composants
| Composant           | Eureka Client | Config Client |
| ------------------- | ------------- | ------------- |
| Eureka Server       | ❌             | ❌             |
| Config Server       | ✅             | ❌             |
| Gateway             | ✅             | ✅             |
| Environment Service | ✅             | ✅             |
| Control Service     | ✅             | ✅             |
