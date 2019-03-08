package ch.hearc.boutiqueservice.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.boutiqueservice.domaine.model.TypeBiere;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.TypeBiereEntity;

public interface TypeBiereSpringDataRepository extends CrudRepository<TypeBiereEntity, Long> {

}
