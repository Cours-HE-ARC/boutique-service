package ch.hearc.boutiqueservice.infrastructure.jpa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.boutiqueservice.domaine.model.PanierStatus;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.ArticleEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.PanierEntity;

public interface PanierSpringDataRepository extends CrudRepository<PanierEntity, Long> {

	public Optional<PanierEntity> findByNoPanier(String noPanier);
	
	public int countByStatus(PanierStatus status);
}
