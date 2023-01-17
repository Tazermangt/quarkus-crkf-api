package fr.ot.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.ot.hateoas.HateOas;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "Famille", schema = "dbo", catalog = "CRKF")
@JsonPropertyOrder({"idFamille", "famille", "idClassification"})
public class FamilleEntity extends HateOas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_famille")
    private int idFamille;
    @Basic
    @Column(name = "Famille")
    private String famille;
    @Basic
    @Column(name = "id_classification")
    private int idClassification;
}
