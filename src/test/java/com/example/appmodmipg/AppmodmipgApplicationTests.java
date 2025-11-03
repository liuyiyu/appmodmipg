package com.example.appmodmipg;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("Integration test requiring external PostgreSQL database - skipped during migration validation")
class AppmodmipgApplicationTests {

    @Test
    void contextLoads() {
    }
}
