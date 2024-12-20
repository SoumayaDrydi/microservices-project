package com.example.demandeprop.Repository;

import com.example.demandeprop.Data.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChambreRepository extends JpaRepository<Chambre,Long> {
    List<Chambre> findByHebergementId(Long hebergementId);
    List<Chambre> findByHebergementIdAndDisponibiliteTrue(Long hebergementId); // Filtre ajouté
}
