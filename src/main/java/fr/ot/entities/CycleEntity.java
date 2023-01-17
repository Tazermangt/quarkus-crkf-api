package fr.ot.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Cycle", schema = "dbo", catalog = "CRKF")
public class CycleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_libelle")
    private int idLibelle;
    @Basic
    @Column(name = "libelle")
    private String libelle;
    @Basic
    @Column(name = "cycle")
    private int cycle;
}
