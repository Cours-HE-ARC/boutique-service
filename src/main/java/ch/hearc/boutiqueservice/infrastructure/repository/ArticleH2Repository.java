package ch.hearc.boutiqueservice.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hearc.boutiqueservice.domaine.model.Article;
import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.model.Stock;
import ch.hearc.boutiqueservice.domaine.repository.ArticleRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.ArticleSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.ArticleEntity;

@Repository
public class ArticleH2Repository implements ArticleRepository {

	private ArticleSpringDataRepository articleSpringDataRepository;
	
	@Autowired
	public ArticleH2Repository(ArticleSpringDataRepository articleSpringDataRepository) {
		this.articleSpringDataRepository = articleSpringDataRepository;
	}



	@Override
	public Article getArticleByNoArticle(String noArticle) {
		
		
		ArticleEntity articleEntity = articleSpringDataRepository.findByNoArticle(noArticle).get();
		
		return Article.mapChampsArticle(
				articleEntity.getDescription(),
				articleEntity.getNoArticle(), 
				articleEntity.getActif(),
				articleEntity.getPrix(), 
				new Fabricant(articleEntity.getFabricant().getId(),
						articleEntity.getFabricant().getNom()),
				Stock.creerStock(articleEntity.getStock().getDescription(),articleEntity.getStock().getStock()));
		
	}

}
