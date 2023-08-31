package com.syn.projectsyn2.bugtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Exclude SecurityAutoConfiguration to disable Spring Security
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EntityScan(basePackages = "com.syn.projectsyn2.bugtracker.domain")
@EnableJpaRepositories(basePackages = {"com.syn.projectsyn2.bugtracker.repository"})
@ComponentScan
@Configuration
@EnableAutoConfiguration
public class BugtrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BugtrackerApplication.class, args);
    }
}
