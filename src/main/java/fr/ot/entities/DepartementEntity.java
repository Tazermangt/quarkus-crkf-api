package fr.ot.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.ot.hateoas.HateOas;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Departement", schema = "dbo", catalog = "CRKF")
@JsonPropertyOrder({"idDepartement", "departement", "numeroDepartement"})
public class DepartementEntity extends HateOas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_departement")
    private int idDepartement;
    @Basic
    @Column(name = "numero_departement")
    private String numeroDepartement;
    @Basic
    @Column(name = "Departement")
    private String departement;
}
