package com.example.appmodmipg.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

@Slf4j
@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class ShardingConfig {

    @Resource
    private DataSource1Config dataSource1Config;
    @Resource
    private DataSource2Config dataSource2Config;

    @Value("${spring.shardingsphere.active-datasource:dataSource1}")
    private String activeDataSource;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        if ("dataSource1".equals(activeDataSource)) {
            hikariDataSource.setDriverClassName(dataSource1Config.getDriverClassName());
            hikariDataSource.setJdbcUrl(dataSource1Config.getUrl());
            // Comment out setUsername and setPassword because PostgreSQL now authenticates using managed identity
            // hikariDataSource.setUsername(dataSource1Config.getUsername());
            // hikariDataSource.setPassword(dataSource1Config.getPassword());
            hikariDataSource.setMaxLifetime(dataSource1Config.getMaxLifeTime());
            hikariDataSource.setConnectionTimeout(dataSource1Config.getConnectionTimeout());
            log.info("Configured datasource: PostgreSQL ({})", dataSource1Config.getUrl());
        } else if ("dataSource2".equals(activeDataSource)) {
            hikariDataSource.setDriverClassName(dataSource2Config.getDriverClassName());
            hikariDataSource.setJdbcUrl(dataSource2Config.getUrl());
            hikariDataSource.setUsername(dataSource2Config.getUsername());
            hikariDataSource.setPassword(dataSource2Config.getPassword());
            log.info("Configured datasource: SQL Server ({})", dataSource2Config.getUrl());
        } else {
            throw new IllegalArgumentException("Invalid active datasource: " + activeDataSource + ". Must be 'dataSource1' or 'dataSource2'");
        }

        return hikariDataSource;
    }
}
