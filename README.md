# CarFlow User Service

**Service de gestion des utilisateurs et authentification**

## 📋 Description

Le User Service gère l'enregistrement, la connexion, le profil utilisateur et les permissions. C'est le service d'identité de CarFlow.

## 🎯 Responsabilités

- Enregistrement et authentification des utilisateurs
- Gestion des profils (clients, locataires, admins)
- Validation des credentials
- Génération et validation des tokens JWT
- Récupération des données utilisateur

## 🛠️ Stack Technique

- **Framework** : Spring Boot 3.5.7
- **Language** : Java 21+
- **Base de données** : MySQL
- **ORM** : JPA

## 📦 Dépendances Principales

```xml
<!-- Spring Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- JWT -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
</dependency>

<!-- Database -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```


## 🚀 Démarrage

```bash
# Build
mvn clean package

# Run
java -jar target/carflow-user-service-*.jar

# Dev
mvn spring-boot:run
```

## 📡 Endpoints Principaux

À compléter : Endpoints complets avec exemples

- `POST /api/users/register` - Créer un nouvel utilisateur
- `POST /api/users/login` - Authentification
- `GET /api/users/{id}` - Récupérer le profil utilisateur
- `PUT /api/users/{id}` - Modifier le profil
- `DELETE /api/users/{id}` - Supprimer le compte
- `POST /api/users/validate-token` - Valider un JWT


## 📊 Modèles de Données

À compléter 


## 🤝 Communication Externe

À compléter : Appels aux autres services, events


## 📚 Documentation

À compléter : Convention de nommage, formatos des erreurs

## 👨‍💻 Auteur

Pierrick VIRET
Gabriel ALLOZA
