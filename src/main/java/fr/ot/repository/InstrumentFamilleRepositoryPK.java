package fr.ot.repository;

import fr.ot.entities.InstrumentFamilleEntityPK;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("InstrumentFamilleRepositoryPK")
@ApplicationScoped
public class InstrumentFamilleRepositoryPK implements PanacheRepositoryBase <InstrumentFamilleEntityPK, Integer> {
}
