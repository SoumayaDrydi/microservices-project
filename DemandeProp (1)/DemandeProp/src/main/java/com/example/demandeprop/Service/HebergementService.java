package com.example.demandeprop.Service;

import com.example.demandeprop.Data.Chambre;
import com.example.demandeprop.Data.Hebergement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "HEBERGEMENT-SERVICE")
public interface HebergementService {

    @GetMapping("Hebergement/{id}")
    public Hebergement getbyid(@PathVariable Long id);

    @PostMapping("/Hebergement")
    public Hebergement saveHergement(Hebergement hebergement);

    // New method to get chambres by hebergement ID
    @GetMapping("/hebergement/{id}/chambres")
    public List<Chambre> getChambresByHebergementId(@PathVariable Long id);


}
