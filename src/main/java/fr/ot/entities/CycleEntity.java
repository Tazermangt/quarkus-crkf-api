package fr.ot.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.ot.hateoas.HateOas;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Cycle", schema = "dbo", catalog = "CRKF")
@JsonPropertyOrder({"idLibelle", "libelle", "cycle"})
public class CycleEntity extends HateOas {
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
