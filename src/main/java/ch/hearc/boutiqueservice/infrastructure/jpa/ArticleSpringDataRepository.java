package ch.hearc.boutiqueservice.infrastructure.jpa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.hearc.boutiqueservice.domaine.model.Article;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.ArticleEntity;

@Repository
public interface ArticleSpringDataRepository extends CrudRepository<ArticleEntity, Long>{
	
	public Optional<ArticleEntity> findByNoArticle(String noArticle);

}
