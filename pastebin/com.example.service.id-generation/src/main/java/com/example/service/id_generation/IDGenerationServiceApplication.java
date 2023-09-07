package com.example.service.id_generation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.example.service.id_generation")
//@EnableJpaRepositories(basePackages = "com.example.service.write.dao")
//@EnableElasticsearchRepositories(basePackages = "com.example.service.write.repository")
@EntityScan(basePackages = "com.example.service.id_generation")
@EnableScheduling
public class IDGenerationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IDGenerationServiceApplication.class, args);
    }
}
