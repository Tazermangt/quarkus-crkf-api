package fr.ot.repository;

import fr.ot.entities.VilleEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("VilleRepository")
@ApplicationScoped
public class VilleRepository implements PanacheRepositoryBase <VilleEntity, Integer> {
}
