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


🟢 Microservice 1 : Environment Service
1️⃣ Objectifs du microservice (liés à l’énoncé du prof)
🎯 Objectif principal

Le microservice Environment est responsable de la gestion des paramètres environnementaux d’une serre connectée et de la collecte des mesures en temps réel.

📌 Lien direct avec l’énoncé

Microservice Environnement : Paramètre, Mesure
Contrôle automatique du climat de la serre
Communication synchrone et asynchrone

Ce microservice permet de :

Définir les paramètres climatiques (température, humidité, luminosité)

Enregistrer les mesures captées par les capteurs

Détecter les dépassements de seuils

Publier des événements RabbitMQ vers le microservice Contrôle

Exposer des API REST consommées par :

API Gateway

Microservice Contrôle (synchrone)
2️⃣ Responsabilités fonctionnelles
📦 Entités métier
Paramètre

id

type (TEMPERATURE, HUMIDITY, LUMINOSITY)

seuilMin

seuilMax

Mesure

id

parametreId

valeur

dateMesure

🔁 Rôle dans l’architecture globale
Interaction	Type
Envoi des mesures vers Contrôle	Asynchrone (RabbitMQ)
Fourniture des dernières mesures	Synchrone (REST)
Découverte de services	Eureka
Configuration	Config Server
Accès externe	API Gateway
3️⃣ Dashboard à créer (pour le Front / démo)

Même si Angular viendra plus tard, le service doit être prêt.

📊 Données exposées au dashboard :

Liste des paramètres configurés

Dernières mesures par type

État des seuils :

✅ Normal

⚠️ Alerte seuil dépassé

👉 Le backend expose des endpoints REST clairs, consommables par Angular.