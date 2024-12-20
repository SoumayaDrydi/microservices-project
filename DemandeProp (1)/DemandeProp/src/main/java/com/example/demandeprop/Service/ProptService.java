package com.example.demandeprop.Service;

import com.example.demandeprop.Data.Proprietaire;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PROPT-SERVICE")
public interface ProptService {

    @GetMapping("Proprietaire/{id}")
    public Proprietaire getbyid(@PathVariable Long id);
}
