package ch.hearc.boutiqueservice.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.hearc.boutiqueservice.infrastructure.repository.entity.ElementPanierEntity;

@Repository
public interface ElementsPanierSpringDataRepository extends CrudRepository<ElementPanierEntity, Long>{

}
