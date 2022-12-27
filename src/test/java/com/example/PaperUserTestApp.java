package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class PaperUserTestApp {

    public static void main(String[] args) {
        SpringApplication.run(PaperUserTestApp.class, args);
    }


    @Configuration
    @ComponentScan("com.example.user")
    @EnableJpaRepositories(basePackages = {
            "com.example.user.repository"
    })
    @EntityScan(basePackages = {
            "com.example.user.domain"
    })
    class Config{

    }
}
