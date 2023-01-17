package fr.ot.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Instrument", schema = "dbo", catalog = "CRKF")
public class InstrumentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_instrument")
    private int idInstrument;
    @Basic
    @Column(name = "Nom")
    private String nom;

}
