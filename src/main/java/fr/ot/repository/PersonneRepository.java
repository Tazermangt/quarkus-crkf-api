package fr.ot.repository;

import fr.ot.entities.PersonneEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("PersonneRepository")
@ApplicationScoped
public class PersonneRepository implements PanacheRepositoryBase <PersonneEntity, Integer> {
}
