# AppModMipg - Spring Boot Application

A Spring Boot application with Azure PostgreSQL database using Managed Identity authentication for Azure China (21Vianet).

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Azure PostgreSQL database (for production)
- Docker (for local testing with SQL Server)

## Configuration

### Azure Deployment (Production)

This application is configured to use Azure Managed Identity for PostgreSQL authentication in Azure China (21Vianet) region.

**Required Environment Variables:**
- `PGHOST`: Azure PostgreSQL server hostname
- `PGPORT`: Azure PostgreSQL server port (typically 5432)
- `PGDATABASE`: Database name
- `MANAGED_IDENTITY_NAME`: Name of the Azure Managed Identity
- `MANAGED_IDENTITY_CLIENT_ID`: Client ID of the Azure Managed Identity

**Azure Configuration:**
- Authentication: Managed Identity (passwordless)
- Region: Azure China (21Vianet)
- Authority Host: `https://login.partner.microsoftonline.cn`
- OAuth Scope: `https://ossrdbms-aad.database.chinacloudapi.cn/.default`

### Local Development/Testing

For local testing, the application can be configured to use SQL Server (dataSource2) by setting:
```yaml
spring.shardingsphere.active-datasource: dataSource2
```

Or for local PostgreSQL testing without managed identity:
```bash
# Note: Local PostgreSQL setup requires modifying application.yml to use local credentials
docker run -d \
  --name appmodmipg-postgres \
  -e POSTGRES_DB=testdb \
  -e POSTGRES_USER=testuser \
  -e POSTGRES_PASSWORD=testpass \
  -p 5432:5432 \
  postgres:15-alpine
```

⚠️ **Important**: The production configuration uses Azure Managed Identity. For local development, you'll need to either:
1. Use the SQL Server datasource configuration (dataSource2), or
2. Temporarily modify the JDBC URL to use local credentials

## Quick Start

### 1. Build the application

```bash
mvn clean package -DskipTests
```

### 2. Run the application

**For Azure deployment:**
```bash
# Ensure environment variables are set
export PGHOST=<your-azure-postgresql-server>.postgres.database.chinacloudapi.cn
export PGPORT=5432
export PGDATABASE=<your-database-name>
export MANAGED_IDENTITY_NAME=<your-managed-identity-name>
export MANAGED_IDENTITY_CLIENT_ID=<your-client-id>

mvn spring-boot:run
```

**For local testing:**
```bash
# Configure to use SQL Server or local PostgreSQL in application.yml
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
```

## Azure Managed Identity Setup

To use this application with Azure Managed Identity:

1. **Create Azure PostgreSQL Flexible Server** in Azure China region
2. **Enable Microsoft Entra (AAD) Authentication** on the PostgreSQL server
3. **Create or assign a Managed Identity** to your application (App Service, Container App, etc.)
4. **Grant database access** to the managed identity:
   ```sql
   SELECT * FROM pgaadauth_create_principal('<managed-identity-name>', false, false);
   GRANT ALL PRIVILEGES ON DATABASE <database-name> TO "<managed-identity-name>";
   ```
5. **Set environment variables** in your Azure App Service / Container App configuration

## Architecture

- **Database**: Azure PostgreSQL Flexible Server (Azure China)
- **Authentication**: Azure Managed Identity (passwordless)
- **Framework**: Spring Boot 3.2.0
- **Build Tool**: Maven
- **Java Version**: 17

