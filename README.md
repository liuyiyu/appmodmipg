
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
# Set environment variables
export PGHOST=pgsqlsvr1216.postgres.database.chinacloudapi.cn
export PGPORT=5432
export PGDATABASE=postgres
export MANAGED_IDENTITY_NAME=mi-1202
export MANAGED_IDENTITY_CLIENT_ID=ad474391-fd73-4024-8eb2-ea92b1f6a396

# Package the application
mvn clean install

# Run the application
java -cp ./target/demo-1.0-SNAPSHOT.jar com.example.Main
```
