package fr.ot.repository;

import fr.ot.entities.FamilleEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("FamilleRepository")
@ApplicationScoped
public class FamilleRepository implements PanacheRepositoryBase <FamilleEntity, Integer> {
}
