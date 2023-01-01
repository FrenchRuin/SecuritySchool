package com.example;

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
    @ComponentScan(basePackages = {
            "com.example.user",
            "com.example.paper"
    })
    @EnableJpaRepositories(basePackages = {
            "com.example.user.repository",
            "com.example.paper.repository"
    })
    @EntityScan(basePackages = {
            "com.example.user.domain",
            "com.example.paper.domain"
    })
    class Config{

    }
}
