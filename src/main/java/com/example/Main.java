package com.example;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner run(DataSource dataSource) {
        return args -> {
            try (Connection connection = dataSource.getConnection()) {
                System.out.println("Successfully connected to PostgreSQL!");
                System.out.println("connection = " + connection.toString());
            } catch (Exception e) {
                System.err.println("Failed to connect to PostgreSQL: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }

}

