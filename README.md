
# PostgreSQL Example

This example shows how to connect to Azure Database for PostgreSQL in a Java project.

## Prerequisites

1. JDK 17
2. Maven
3. Required Azure resources, including:
	- A subscription to Azure China Cloud.
	- An Azure user account (with access to the PostgreSQL server).
	- A PostgreSQL server (supports authentication by PostgreSQL).

## How to Run This Example

```shell
# Log in to Azure China
az cloud set --name AzureChinaCloud
az login --username <your-username> --password <your-password>

# Set environment variables
export PGHOST=<your-db-server>.postgres.database.chinacloudapi.cn
export PGUSER=<your-username>@cecccic.partner.onmschina.cn
export PGPORT=5432
export PGDATABASE=postgres
export PGPASSWORD="$(az account get-access-token --resource https://ossrdbms-aad.database.chinacloudapi.cn --query accessToken --output tsv)"

# Package the application
mvn clean install

# Run the application
java -cp ./target/demo-1.0-SNAPSHOT.jar com.example.Main
```
