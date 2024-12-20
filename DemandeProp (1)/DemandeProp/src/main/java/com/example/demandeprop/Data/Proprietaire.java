package com.example.demandeprop.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "proprietaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Proprietaire implements Serializable {

    @Id
    private Long id;
    private String nom;
    private String prenom;

    @JsonIgnore
    @OneToMany(mappedBy = "proprietaire",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Hebergement> hebergements;
}
