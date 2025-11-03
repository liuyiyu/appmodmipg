package com.example.appmodmipg.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@TestConfiguration
@Profile("test")
public class TestDataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        // Return a mock or test datasource that doesn't require actual database connection
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName("org.postgresql.Driver");
        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/testdb");
        hikariDataSource.setUsername("testuser");
        hikariDataSource.setPassword("testpass");
        hikariDataSource.setMaxLifetime(30000);
        hikariDataSource.setConnectionTimeout(20000);
        return hikariDataSource;
    }
}
