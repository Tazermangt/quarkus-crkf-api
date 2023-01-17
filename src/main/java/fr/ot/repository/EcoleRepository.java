package fr.ot.repository;

import fr.ot.entities.EcoleEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("EcoleRepository")
@ApplicationScoped
public class EcoleRepository implements PanacheRepositoryBase <EcoleEntity, Integer> {
}
