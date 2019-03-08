package ch.hearc.boutiqueservice.infrastructure.jpa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ch.hearc.boutiqueservice.infrastructure.repository.entity.ArticleEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.BiereEntity;

@Repository
public interface BiereSpringDataRepository extends CrudRepository<BiereEntity, Long> {

	public Optional<BiereEntity> findByArticle_NoArticle(String noArticle);
}
