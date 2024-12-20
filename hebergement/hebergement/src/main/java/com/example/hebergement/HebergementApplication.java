package com.example.hebergement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.hebergement.Data")
public class HebergementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HebergementApplication.class, args);
    }

}
