package com.example.javeke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableMongoRepositories
public class JavekeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavekeApplication.class, args);
    }

}
