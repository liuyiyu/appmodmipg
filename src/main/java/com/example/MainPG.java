package com.example;

//import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.azure.core.credential.TokenRequestContext;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;

public class MainPG {

    


    public static void main(String[] args) throws Exception {


        Properties properties = new Properties();
        try (InputStream input = MainPG.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            // Load the properties file
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        String connString = properties.getProperty("AZURE_PGSQL_CONNECTIONSTRING");
        String userName = properties.getProperty("azure.username");
        String clientId = properties.getProperty("azure.clientid");
   

        // Create the DefaultAzureCredential instance
        DefaultAzureCredential credential = new DefaultAzureCredentialBuilder().build();

        // Obtain an access token
        String accessToken = credential.getToken(new TokenRequestContext().addScopes("https://ossrdbms-aad.database.chinacloudapi.cn/.default")).block().getToken();

        
        connString = connString + "&user=" + userName + "&password=" + accessToken;
        Connection connection = DriverManager.getConnection(connString);
        
        System.out.println(connection.toString());
        

    }

    
}