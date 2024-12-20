package com.example.demandeprop.Service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SERVICE-CLIENT")
public interface ClientService {
}
