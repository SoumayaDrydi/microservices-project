package com.example.demandeprop.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hebergement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hebergement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String localisation;
    private String telephone;
    private String fax;
    private String etoile;
    private String email;
    private String type;
    private String description;
    private boolean entertaiment;
    private boolean pescine;
    private boolean parking;
    private boolean restauration;
    private boolean serviceChambre;
    private boolean wifi;
    private String etat;
    private Double prix;
    private boolean dispo;

    @JsonIgnore
    @JsonBackReference
    @ManyToOne
    private Proprietaire proprietaire;

    @JsonIgnore
    @JsonBackReference
    @ManyToOne
    private Admin admin;

    @JsonIgnore
    @JsonBackReference
    @OneToMany(mappedBy = "hebergement",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Chambre> chambres;

    @JsonIgnore
    @OneToMany(mappedBy = "hebergementImg",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();



}
