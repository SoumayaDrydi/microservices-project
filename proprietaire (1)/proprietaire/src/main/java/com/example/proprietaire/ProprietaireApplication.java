package com.example.proprietaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.proprietaire.Data")
public class ProprietaireApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProprietaireApplication.class, args);
    }

}
