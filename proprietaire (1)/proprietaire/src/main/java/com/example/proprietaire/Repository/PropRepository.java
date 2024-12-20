package com.example.proprietaire.Repository;

import com.example.proprietaire.Data.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropRepository extends JpaRepository<Proprietaire,Long> {
}
