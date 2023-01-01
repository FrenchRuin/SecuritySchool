package com.example.securitySchool.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.example.securitySchool.user")
@EnableJpaRepositories(basePackages = {
        "com.example.securitySchool.user.repository"
})
@EntityScan(basePackages = {
        "com.example.securitySchool.user.domain"
})
public class PaperUserModule {
}
