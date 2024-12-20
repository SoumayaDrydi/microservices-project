package com.example.demandeprop.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "Chambre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Chambre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nbPersonne;

    private Double espace;

    private String Bed;

    private boolean disponibilite;

    private boolean climatisation;

    private boolean balcon;

    private boolean televesion;

    private boolean wifi;

    private boolean salledebains;

    private Double prix;

    private Integer reservationId; // Peut être null si non réservé

    @JsonIgnore
    @JsonBackReference
    @ManyToOne
    private Hebergement hebergement;
}
