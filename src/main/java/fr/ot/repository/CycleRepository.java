package fr.ot.repository;

import fr.ot.entities.CycleEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("CycleRepository")
@ApplicationScoped
public class CycleRepository implements PanacheRepositoryBase <CycleEntity, Integer> {
}
