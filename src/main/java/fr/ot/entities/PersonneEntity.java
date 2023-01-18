package fr.ot.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.ot.hateoas.HateOas;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Personne", schema = "dbo", catalog = "CRKF")
@JsonPropertyOrder({"idPersonne", "nom", "prenom", "vehiculeCv", "idAdresse", "idEcole"})
public class PersonneEntity extends HateOas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_personne")
    private int idPersonne;
    @Basic
    @Column(name = "Nom")
    private String nom;
    @Basic
    @Column(name = "Prenom")
    private String prenom;
    @Basic
    @Column(name = "VehiculeCV")
    private int vehiculeCv;
    @Basic
    @Column(name = "id_adresse")
    private int idAdresse;
    @Basic
    @Column(name = "id_ecole")
    private int idEcole;
}
