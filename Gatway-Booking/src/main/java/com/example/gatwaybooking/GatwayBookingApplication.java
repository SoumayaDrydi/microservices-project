package com.example.gatwaybooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class GatwayBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatwayBookingApplication.class, args);
	}
	@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r->r.path("/Admin/**").uri("lb://ADMIN-SERVICE/"))
				.route(r->r.path("/Proprietaire/**").uri("lb://PROPT-SERVICE/"))
				.route(r->r.path("/Hebergement/**").uri("lb://HEBERGEMENT-SERVICE/"))
				.route(r->r.path("/api/chambres/**").uri("lb://SERVICE-CHAMBRE/"))
				.route(r->r.path("/Demande/**").uri("lb://DEMANDE-SERVICE/"))
				.route(r->r.path("/api/reservations/**").uri("lb://SERVICE-RESERVATION/"))
				.build();
	}
}
