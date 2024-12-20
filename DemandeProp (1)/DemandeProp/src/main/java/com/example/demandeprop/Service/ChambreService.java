package com.example.demandeprop.Service;

import com.example.demandeprop.Data.Chambre;
import com.example.demandeprop.Data.Hebergement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "SERVICE-CHAMBRE")
public interface ChambreService {

    @GetMapping("/{id}")
    public Chambre getbyid(@PathVariable Long id);
}
