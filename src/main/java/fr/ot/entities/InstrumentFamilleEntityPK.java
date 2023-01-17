package fr.ot.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class InstrumentFamilleEntityPK implements Serializable {
    @Column(name = "id_instrument")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInstrument;
    @Column(name = "id_famille")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFamille;
}
