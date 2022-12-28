package com.example.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.example.paper")
@EnableJpaRepositories(basePackages = {
        "com.example.paper.repository"
})
@EntityScan(basePackages = {
        "com.example.paper.domain"
})
public class PaperModule {
}
