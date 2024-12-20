package com.example.demandeprop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemandePropApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemandePropApplication.class, args);
	}

}
