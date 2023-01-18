package fr.ot.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.ot.hateoas.HateOas;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Compte", schema = "dbo", catalog = "CRKF")
@JsonPropertyOrder({"idCompte", "email", "password"})
public class CompteEntity extends HateOas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_compte")
    private int idCompte;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
}
