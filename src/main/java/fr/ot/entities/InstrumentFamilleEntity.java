package fr.ot.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Instrument_Famille", schema = "dbo", catalog = "CRKF")
@IdClass(InstrumentFamilleEntityPK.class)
public class InstrumentFamilleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_instrument")
    private int idInstrument;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_famille")
    private int idFamille;
}
