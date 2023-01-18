package fr.ot.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.ot.hateoas.HateOas;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Adresse", schema = "dbo", catalog = "CRKF")
@JsonPropertyOrder({"idAdresse", "adresse", "idVille"})
public class AdresseEntity extends HateOas {
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
