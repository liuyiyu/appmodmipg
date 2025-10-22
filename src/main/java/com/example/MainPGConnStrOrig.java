package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainPGConnStrOrig {

    


    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        try (InputStream input = MainPGConnStr.class.getClassLoader().getResourceAsStream("application.properties")) {
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
        

        Connection connection = DriverManager.getConnection(connString);
        

        System.out.println(connection.toString());
        

    }

    
}