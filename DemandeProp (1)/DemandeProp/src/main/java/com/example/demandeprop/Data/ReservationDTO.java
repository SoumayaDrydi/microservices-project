package com.example.demandeprop.Data;

import lombok.Data;

import java.util.List;

@Data
public class ReservationDTO {
    private Integer id;
    private Integer clientId;
    private String dateArrivee;
    private String dateDepart;
    private String statut;
    private Double montantTotal;
    private Long hebergementId;
    private List<Chambre> chambres; // Détails des chambres
}
