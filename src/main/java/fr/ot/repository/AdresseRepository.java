package fr.ot.repository;

import fr.ot.entities.AdresseEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("AdresseRepository")
@ApplicationScoped
public class AdresseRepository implements PanacheRepositoryBase <AdresseEntity, Integer> {
}
