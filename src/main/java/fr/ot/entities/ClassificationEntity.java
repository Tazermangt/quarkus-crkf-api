package fr.ot.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "Classification", schema = "dbo", catalog = "CRKF")
public class ClassificationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_classification")
    private int idClassification;
    @Basic
    @Column(name = "Classification")
    private String classification;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassificationEntity that = (ClassificationEntity) o;
        return idClassification == that.idClassification && Objects.equals(classification, that.classification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClassification, classification);
    }
}
