package ch.hearc.boutiqueservice.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.FabricantEntity;

public interface FabricantSpringDataRepository extends CrudRepository<FabricantEntity, Long> {

}
