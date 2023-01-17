package fr.ot.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.ot.hateoas.HateOas;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Ville", schema = "dbo", catalog = "CRKF")
@JsonPropertyOrder({"idVille", "ville", "longitude", "latitude", "idDepartement"})
public class VilleEntity extends HateOas {
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
