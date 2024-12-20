package com.example.hebergement.Controller;

import com.example.hebergement.Data.Hebergement;
import com.example.hebergement.Repository.HebergementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Hebergement")
@CrossOrigin("*")
public class Controller {

    @Autowired
    public HebergementRepository hebergementRepository;

    @GetMapping
    public List<Hebergement> getAll(){
        return hebergementRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hebergement getbyid(@PathVariable Long id){
        return hebergementRepository.findById(id).get();
    }

    @PostMapping
    public Hebergement saveHebergement(@RequestBody Hebergement newHebergement){
        return hebergementRepository.save(newHebergement);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id){
        hebergementRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public  Hebergement updateAdmin(@PathVariable Long id,@RequestBody Hebergement newHebergement){
        newHebergement.setId(id);
        return hebergementRepository.save(newHebergement);
    }
}
