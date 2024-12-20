package com.example.demandeprop.Data;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;


import java.util.List;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer clientId;
    private String dateArrivee;
    private String dateDepart;
    private String statut;
    private Double montantTotal;
    private Long hebergementId;
    @Type(JsonType.class) // Utilise le type JSON pour le mapping
    @Column(columnDefinition = "json", nullable = false)
    private List<Long> chambres; // Liste des IDs des chambres
}
