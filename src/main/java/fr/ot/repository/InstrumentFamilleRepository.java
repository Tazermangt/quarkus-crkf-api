package fr.ot.repository;

import fr.ot.entities.InstrumentFamilleEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("InstrumentFamilleRepository")
@ApplicationScoped
public class InstrumentFamilleRepository implements PanacheRepositoryBase <InstrumentFamilleEntity, Integer> {
}
