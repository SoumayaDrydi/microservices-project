package com.example.demandeprop.Repository;

import com.example.demandeprop.Data.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropRepository extends JpaRepository<Proprietaire,Long> {
}
