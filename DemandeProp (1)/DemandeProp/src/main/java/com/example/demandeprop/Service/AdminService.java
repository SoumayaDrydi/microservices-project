package com.example.demandeprop.Service;

import com.example.demandeprop.Data.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ADMIN-SERVICE")
public interface AdminService {

    @GetMapping("Admin/{id}")
    public Admin getbyid(@PathVariable Long id);
}
