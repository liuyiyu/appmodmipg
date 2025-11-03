package com.example.appmodmipg;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Disabled("Integration test - requires external database connection (PostgreSQL or SQL Server)")
class AppmodmipgApplicationTests {

    @Test
    void contextLoads() {
    }
}
