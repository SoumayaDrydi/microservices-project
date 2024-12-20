package com.example.demandeprop.Service;

import com.example.demandeprop.Data.Chambre;
import com.example.demandeprop.Data.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SERVICE-RESERVATION")
public interface ReservationService {

    @GetMapping("/")
    public Reservation getAll();
}
