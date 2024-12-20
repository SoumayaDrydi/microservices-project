package com.example.admin.Controller;

import com.example.admin.Data.Admin;
import com.example.admin.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
@CrossOrigin("*")
public class Controller {

    @Autowired
    AdminRepository adminRepository;

    @GetMapping
    public List<Admin> getAll(){
        return adminRepository.findAll();
    }

    @GetMapping("/{id}")
    public Admin getbyid(@PathVariable Long id){
        return adminRepository.findById(id).get();
    }

    @PostMapping
    public Admin saveAdmin(@RequestBody Admin newAdmin){
        return adminRepository.save(newAdmin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id){
        adminRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public  Admin updateAdmin(@PathVariable Long id,@RequestBody Admin newAdmin){
        newAdmin.setId(id);
        return adminRepository.save(newAdmin);
    }
}
