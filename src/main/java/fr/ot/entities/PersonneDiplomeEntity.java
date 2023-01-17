package fr.ot.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Personne_Diplome", schema = "dbo", catalog = "CRKF")
@IdClass(PersonneDiplomeEntityPK.class)
public class PersonneDiplomeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_libelle")
    private int idLibelle;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_personne")
    private int idPersonne;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_instrument")
    private int idInstrument;
}
