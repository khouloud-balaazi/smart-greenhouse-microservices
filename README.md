README.md — Smart Greenhouse Microservices
🌱 Smart Greenhouse – Architecture Microservices

Ce projet implémente une application Smart Greenhouse basée sur une architecture microservices permettant de :

surveiller des paramètres environnementaux (température, humidité, luminosité),

déclencher automatiquement des actions sur des équipements (ventilateur, pompe, chauffage),

communiquer de manière asynchrone via RabbitMQ.

🧱 Architecture Générale

L’architecture repose sur :

Spring Boot (Back-end)

Angular (Front-end)

RabbitMQ (Event-driven communication)

MySQL (Base de données par microservice)

Docker & Docker Compose

Eureka Server (Service Discovery)

Spring Cloud Gateway

Config Server

Kubernetes manifests (préparation déploiement)

🧩 Microservices
🔹 1. Config Server

Centralise les fichiers de configuration (application.properties)

Facilite la gestion multi-environnements (local / docker)

🔹 2. Eureka Server

Registre tous les microservices

Permet la découverte dynamique des services

🔹 3. Gateway Service

Point d’entrée unique de l’application

Redirige les requêtes vers les microservices

🔹 4. Environment Service

Responsabilités :

Gestion des paramètres (TEMPÉRATURE, HUMIDITÉ, etc.)

Gestion des mesures

Publication des événements RabbitMQ (MeasurementEvent)

➡️ À chaque nouvelle mesure, un événement est envoyé vers RabbitMQ.

🔹 5. Controle Service

Responsabilités :

Gestion des équipements (VENTILATEUR, POMPE, CHAUFFAGE)

Gestion des actions

Consommation des événements RabbitMQ

Création automatique d’actions si un seuil est dépassé

📌 Exemple :

Si la température dépasse le seuil max → Action DEMARRER VENTILATEUR

🔁 Communication Asynchrone (RabbitMQ)
📤 Publisher

environment-service publie les événements de mesure

📥 Consumer

controle-service consomme les événements

Analyse les seuils

Déclenche automatiquement des actions

✔️ Avantages :

Services découplés

Scalabilité

Fiabilité

🗃️ Bases de Données

Chaque microservice possède sa base MySQL dédiée :

environment_db

control_db

➡️ Isolation des données (bonne pratique microservices)

🐳 Docker

Le projet est entièrement dockerisé.

Lancer l’application :
cd docker
docker-compose up --build


Services exposés :

Gateway : http://localhost:8085

Eureka : http://localhost:8761

RabbitMQ UI : http://localhost:15672

Environment Service : http://localhost:8089

Controle Service : http://localhost:8087

📊 Frontend (Angular)

Le dashboard permet :

Visualisation des paramètres

Visualisation des équipements

Affichage des mesures

Suivi des actions automatiques

Alertes en temps réel

☸️ Kubernetes (optionnel)

Des fichiers YAML sont fournis pour :

config-server

eureka-server

gateway-service

environment-service

controle-service

rabbitmq

➡️ Préparation au déploiement cloud

🎯 Objectif du Projet

Appliquer les bonnes pratiques microservices

Mettre en place une architecture event-driven

Comprendre la communication asynchrone

Automatiser des décisions métier

Maîtriser Docker & Spring Cloud

👩‍💻 Réalisé par

Khouloud Balaazi
Projet académique – Mini Projet Microservices