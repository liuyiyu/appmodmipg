package com.example;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Main {

    


    public static void main(String[] args) {


        // For system-assigned managed identity: "jdbc:sqlserver://{SQLName}.database.windows.net:1433;databaseName={SQLDbName};authentication=ActiveDirectoryMSI;"
        // For user-assigned managed identity: "jdbc:sqlserver://{SQLName}.database.windows.net:1433;databaseName={SQLDbName};msiClientId={UserAssignedMiClientId};authentication=ActiveDirectoryMSI;"
        
        //String connectionString = System.getenv("AZURE_SQL_CONNECTIONSTRING");
        String connectionString = "jdbc:sqlserver://dbsvr-demodb1.database.windows.net:1433;databaseName=demodb1;msiClientId=8255b7c2-956f-495c-a31d-343d94e9fbc3;authentication=ActiveDirectoryMSI;";
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setURL(connectionString);
        try (Connection connection = ds.getConnection()) {
            System.out.println("Connected successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}