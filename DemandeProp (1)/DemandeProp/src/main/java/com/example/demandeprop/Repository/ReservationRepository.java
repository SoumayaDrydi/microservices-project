package com.example.demandeprop.Repository;

import com.example.demandeprop.Data.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    List<Reservation> findByHebergementIdIn(List<Long> hebergementIds);


}
