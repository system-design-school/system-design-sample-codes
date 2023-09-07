package com.example.service.read;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.example")
//@EnableElasticsearchRepositories(basePackages = "com.example.service.write.repository")
@EntityScan(basePackages = "com.example.pastebin.entity")
@EnableJpaRepositories(basePackages = "com.example.pastebin.dao")
public class ReadServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReadServiceApplication.class, args);
    }
}
