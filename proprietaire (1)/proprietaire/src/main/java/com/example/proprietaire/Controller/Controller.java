package com.example.proprietaire.Controller;

import com.example.proprietaire.Data.Proprietaire;
import com.example.proprietaire.Repository.PropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Proprietaire")
@CrossOrigin("*")
public class Controller {

    @Autowired
    PropRepository propRepository;

    @GetMapping
    public List<Proprietaire> getAll(){
        return propRepository.findAll();
    }

    @GetMapping("/{id}")
    public Proprietaire getbyid(@PathVariable Long id){
        return propRepository.findById(id).get();
    }

    @PostMapping
    public Proprietaire saveAdmin(@RequestBody Proprietaire newProprietaire){
        return propRepository.save(newProprietaire);
    }

    @DeleteMapping("/{id}")
    public void deleteProprietaire(@PathVariable Long id){
        propRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public  Proprietaire updateProprietaire(@PathVariable Long id,@RequestBody Proprietaire newProprietaire){
        newProprietaire.setId(id);
        return propRepository.save(newProprietaire);
    }
}
