package fr.ot.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Ecole", schema = "dbo", catalog = "CRKF")
public class EcoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ecole")
    private int idEcole;
    @Basic
    @Column(name = "Nom")
    private String nom;
    @Basic
    @Column(name = "id_adresse")
    private int idAdresse;
}
