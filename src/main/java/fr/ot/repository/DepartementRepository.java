package fr.ot.repository;

import fr.ot.entities.DepartementEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("DepartementRepository")
@ApplicationScoped
public class DepartementRepository implements PanacheRepositoryBase <DepartementEntity, Integer> {
}
