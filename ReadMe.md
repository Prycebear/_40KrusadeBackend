# ☕ Spring Boot Backend - 40K Crusade API

This is a backend service built using **Java Spring Boot** and **Maven**, designed to support a Warhammer 40K Crusade app. It connects to a **PostgreSQL** database running in Docker.

---

## 📦 Tech Stack

- Java 17+
- Spring Boot
- Maven
- PostgreSQL (via Docker)
- Docker & Docker Compose
- Spring Security
- HikariCP connection pooling

---

## 🚀 Getting Started

### ✅ Prerequisites

Make sure the following tools are installed:

- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Docker & Docker Compose](https://www.docker.com/)

---

## 🐳 Setting Up the Database with Docker

This project includes a `docker-compose.yml` file to spin up a PostgreSQL database.

### 🔧 `docker-compose.yml`

```yaml
version: '3.8'  # Specify the version of Docker Compose

services:
  postgres:
    image: postgres:latest
    container_name: 40krusadeDB
    restart: unless-stopped
    environment:
      POSTGRES_USER: prycebear
      POSTGRES_PASSWORD: Ashashcat1
      POSTGRES_DB: 40krusadeDB
    ports:
      - "5434:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data

volumes:
  pgdata:  # This volume will store the database data

# Start the database
docker-compose up -d

# Stop and remove containers
docker-compose down

# Stop containers without removing them
docker-compose stop

# View logs
docker-compose logs postgres

# Restart only the DB container
docker-compose restart postgres