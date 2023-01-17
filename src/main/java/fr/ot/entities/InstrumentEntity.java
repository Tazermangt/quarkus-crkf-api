package fr.ot.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.ot.hateoas.HateOas;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Instrument", schema = "dbo", catalog = "CRKF")
@JsonPropertyOrder({"idInstrument", "nom"})
public class InstrumentEntity extends HateOas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_instrument")
    private int idInstrument;
    @Basic
    @Column(name = "Nom")
    private String nom;

}
