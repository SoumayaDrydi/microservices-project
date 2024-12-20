package com.example.demandeprop.Repository;

import com.example.demandeprop.Data.Hebergement;
import com.example.demandeprop.Data.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HebergementReository extends JpaRepository<Hebergement,Long> {

    Hebergement findByProprietaire(Proprietaire proprietaire);
    List<Hebergement> findByEtat(String etat);

    List<Hebergement> findByDispo(boolean dispo);

    Optional<Hebergement> findById(Long id);

    // Nouvelle méthode pour trouver les hébergements par ID de propriétaire
    List<Hebergement> findByProprietaireId(Long proprietaireId);
    List<Hebergement> findByLocalisationContainingIgnoreCase(String localisation);
}

