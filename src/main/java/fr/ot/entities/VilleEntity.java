package fr.ot.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Ville", schema = "dbo", catalog = "CRKF")
public class VilleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ville")
    private int idVille;
    @Basic
    @Column(name = "Ville")
    private String ville;
    @Basic
    @Column(name = "Longitude")
    private double longitude;
    @Basic
    @Column(name = "Latitude")
    private double latitude;
    @Basic
    @Column(name = "id_departement")
    private int idDepartement;
}
