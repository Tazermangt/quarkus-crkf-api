package fr.ot.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Famille", schema = "dbo", catalog = "CRKF")
public class FamilleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_famille")
    private Integer id;

    @Column (name = "Famille")
    private String nom;

    @Column (name = "id_classification")
    private Integer id_classification;
}
