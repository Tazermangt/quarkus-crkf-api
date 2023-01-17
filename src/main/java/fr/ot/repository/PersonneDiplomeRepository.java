package fr.ot.repository;

import fr.ot.entities.PersonneDiplomeEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("PersonneDiplomeRepository")
@ApplicationScoped
public class PersonneDiplomeRepository implements PanacheRepositoryBase <PersonneDiplomeEntity, Integer> {
}
