package fr.ot.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.ot.hateoas.HateOas;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Ecole", schema = "dbo", catalog = "CRKF")
@JsonPropertyOrder({"idEcole", "nom", "idAdresse"})
public class EcoleEntity extends HateOas {
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
