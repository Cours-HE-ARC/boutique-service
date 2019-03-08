package ch.hearc.boutiqueservice.infrastructure.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.hearc.boutiqueservice.domaine.model.Article;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.ArticleEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.StockEntity;

@Repository
public interface StockSpringDataRepository extends CrudRepository<StockEntity, Long>{

}
