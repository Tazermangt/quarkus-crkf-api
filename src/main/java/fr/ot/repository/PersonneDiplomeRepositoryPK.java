package fr.ot.repository;

import fr.ot.entities.PersonneDiplomeEntityPK;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("PersonneDiplomeRepositoryPK")
@ApplicationScoped
public class PersonneDiplomeRepositoryPK implements PanacheRepositoryBase <PersonneDiplomeEntityPK, Integer> {
}
