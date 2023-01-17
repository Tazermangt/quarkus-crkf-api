package fr.ot.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class PersonneDiplomeEntityPK implements Serializable {
    @Column(name = "id_libelle")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLibelle;
    @Column(name = "id_personne")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersonne;
    @Column(name = "id_instrument")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInstrument;
}
