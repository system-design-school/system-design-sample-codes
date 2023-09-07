package com.example.service.write;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.example")
@EntityScan(basePackages = "com.example.pastebin.entity")
@EnableJpaRepositories(basePackages = "com.example.pastebin.dao")
@EnableScheduling
public class WriteServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WriteServiceApplication.class, args);
    }
}
