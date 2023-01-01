package com.example.securitySchool.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.example.securitySchool.paper")
@EnableJpaRepositories(basePackages = {
        "com.example.securitySchool.paper.repository"
})
@EntityScan(basePackages = {
        "com.example.securitySchool.paper.domain"
})
public class PaperModule {
}
