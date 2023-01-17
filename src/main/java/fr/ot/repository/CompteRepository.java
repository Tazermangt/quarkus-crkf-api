package fr.ot.repository;

import fr.ot.entities.CompteEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("CompteRepository")
@ApplicationScoped
public class CompteRepository implements PanacheRepositoryBase <CompteEntity, Integer> {
}
