# AppModMipg - Spring Boot Application

A Spring Boot application with PostgreSQL database using Azure Managed Identity for authentication in Mooncake (21V China).

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Azure PostgreSQL Flexible Server in Mooncake (21V China)
- Azure Managed Identity configured with PostgreSQL access

## Azure Managed Identity Setup

This application uses Azure Managed Identity for PostgreSQL authentication instead of username/password credentials. This provides enhanced security by eliminating the need to store database credentials.

### Required Environment Variables

Before running the application, set the following environment variables:

```bash
export PGHOST=<your-azure-postgresql-server>.postgres.database.chinacloudapi.cn
export PGPORT=5432
export PGDATABASE=<your-database-name>
export MANAGED_IDENTITY_NAME=<your-managed-identity-name>
export MANAGED_IDENTITY_CLIENT_ID=<your-managed-identity-client-id>
```

### Azure Configuration Requirements

1. **Azure PostgreSQL Flexible Server** must be deployed in Mooncake (21V China)
2. **Managed Identity** must be created and configured:
   - System-assigned or user-assigned managed identity
   - Identity must have appropriate database access permissions
3. **Azure AD Authentication** must be enabled on the PostgreSQL server
4. **Firewall rules** must allow connections from your application

## Quick Start

### 1. Configure Environment Variables

Set all required environment variables as described above.

### 2. Build and run the application

```bash
mvn clean package -DskipTests
mvn spring-boot:run
```

## Local Development (Without Azure)

For local development without Azure infrastructure:

1. Update `src/main/resources/application.yml`
2. Uncomment the username and password properties
3. Update the JDBC URL to use local PostgreSQL:
   ```yaml
   url: jdbc:postgresql://localhost:5432/testdb
   username: testuser
   password: testpass
   ```
4. Start local PostgreSQL:
   ```bash
   docker run -d \
     --name appmodmipg-postgres \
     -e POSTGRES_DB=testdb \
     -e POSTGRES_USER=testuser \
     -e POSTGRES_PASSWORD=testpass \
     -p 5432:5432 \
     postgres:15-alpine
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

