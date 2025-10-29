# AppModMipg - Spring Boot Application

A Spring Boot application with PostgreSQL database.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Docker

## Quick Start

### 1. Start PostgreSQL

```bash
docker run -d \
  --name appmodmipg-postgres \
  -e POSTGRES_DB=testdb \
  -e POSTGRES_USER=testuser \
  -e POSTGRES_PASSWORD=testpass \
  -p 5432:5432 \
  postgres:15-alpine
```

### 2. Build and run the application

```bash
mvn clean package -DskipTests
mvn spring-boot:run
```

### 3. Test the application

```bash
# Get all users
curl http://localhost:8080/api/users

# Get all products
curl http://localhost:8080/api/products

# Create a user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@example.com","age":30}'

# Connect to database manually
docker exec -it appmodmipg-postgres psql -U testuser -d testdb
```

## Stop Everything

```bash
# Stop application: Ctrl+C

# Stop and remove PostgreSQL container
docker stop appmodmipg-postgres
docker rm appmodmipg-postgres
```

