package fr.ot.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Departement", schema = "dbo", catalog = "CRKF")
public class DepartementEntity {
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
