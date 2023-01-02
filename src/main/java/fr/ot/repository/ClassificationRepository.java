package fr.ot.repository;

import fr.ot.entities.ClassificationEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("ClassificationRepository")
@ApplicationScoped
public class ClassificationRepository implements PanacheRepositoryBase <ClassificationEntity, Integer> {
}
