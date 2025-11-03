package com.example.appmodmipg.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class DataSource1Config {

    @Value("${spring.shardingsphere.dataSource1.name}")
    private String name;
    @Value("${spring.shardingsphere.dataSource1.driver-class-name}")
    private String driverClassName;
    @Value("${spring.shardingsphere.dataSource1.url}")
    private String url;
    // Comment out username and password because PostgreSQL now authenticates using managed identity
    // @Value("${spring.shardingsphere.dataSource1.username}")
    // private String username;
    // @Value("${spring.shardingsphere.dataSource1.password}")
    // private String password;
    @Value("${spring.shardingsphere.dataSource1.max-life-time}")
    private long maxLifeTime;
    @Value("${spring.shardingsphere.dataSource1.connection-timeout}")
    private long connectionTimeout;

}
