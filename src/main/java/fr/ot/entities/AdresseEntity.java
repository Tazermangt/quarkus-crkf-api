package fr.ot.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Adresse", schema = "dbo", catalog = "CRKF")
public class AdresseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_adresse")
    private int idAdresse;
    @Basic
    @Column(name = "Adresse")
    private String adresse;
    @Basic
    @Column(name = "id_ville")
    private int idVille;
}
