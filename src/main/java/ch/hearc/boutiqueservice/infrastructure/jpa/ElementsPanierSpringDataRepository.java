package ch.hearc.boutiqueservice.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.hearc.boutiqueservice.infrastructure.repository.entity.ArticlesPanierEntity;

@Repository
public interface ElementsPanierSpringDataRepository extends CrudRepository<ArticlesPanierEntity, Long>{

}
