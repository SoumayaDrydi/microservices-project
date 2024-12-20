package com.example.hebergement.Repository;

import com.example.hebergement.Data.Hebergement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HebergementRepository extends JpaRepository<Hebergement,Long> {
}
