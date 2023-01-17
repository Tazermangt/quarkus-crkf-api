package fr.ot.repository;

import fr.ot.entities.InstrumentEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("InstrumentRepository")
@ApplicationScoped
public class InstrumentRepository implements PanacheRepositoryBase <InstrumentEntity, Integer> {
}
