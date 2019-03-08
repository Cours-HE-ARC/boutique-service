package ch.hearc.boutiqueservice.infrastructure.repository;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hearc.boutiqueservice.domaine.model.Article;
import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.model.Panier;
import ch.hearc.boutiqueservice.domaine.model.Stock;
import ch.hearc.boutiqueservice.domaine.repository.PanierRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.ArticleSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.ElementsPanierSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.PanierSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.ArticleEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.ArticlesPanierEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.FabricantEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.PanierEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.StockEntity;

@Repository
public class PanierH2Repository implements PanierRepository{

	@Autowired
	public PanierH2Repository(PanierSpringDataRepository panierSpringDataRepository,ElementsPanierSpringDataRepository elementsPanierSpringDataRepository,ArticleSpringDataRepository articleSpringDataRepository) {
		this.panierSpringDataRepository = panierSpringDataRepository;
		this.elementsPanierSpringDataRepository = elementsPanierSpringDataRepository;
		this.articleSpringDataRepository = articleSpringDataRepository;
	}



	private PanierSpringDataRepository panierSpringDataRepository;
	private ElementsPanierSpringDataRepository elementsPanierSpringDataRepository;
	private ArticleSpringDataRepository articleSpringDataRepository;
	
	
	@Override
	public Panier creerPanier(Panier panier) {
		
		PanierEntity panierEntity = new PanierEntity(panier.getNoPanier(), panier.getStatus());
		
		panierSpringDataRepository.save(panierEntity);
		
		return Panier.mapPanierFromFields(panierEntity.getNoPanier(), panierEntity.getStatus());
		
	}



	@Override
	public Panier getPanierByNoPanier(String noPanier) {
		PanierEntity panierEntity = panierSpringDataRepository.findByNoPanier(noPanier).get();
		
		return Panier.mapPanierFromFields(
				panierEntity.getNoPanier(), 
				panierEntity.getStatus())
				.withArticles(
						panierEntity.getArticles()
							.stream().collect(
									Collectors.toMap(articleEntity -> {
										return Article.mapChampsArticle(
												articleEntity.getArticle().getDescription(), 
												articleEntity.getArticle().getNoArticle(), 
												articleEntity.getArticle().getActif(),
												articleEntity.getArticle().getPrix(),
												new Fabricant(
														articleEntity.getArticle().getFabricant().getId(),
														articleEntity.getArticle().getFabricant().getNom()),
												Stock.creerStock(
														articleEntity.getArticle().getStock().getDescription(), 
														articleEntity.getArticle().getStock().getStock()));
									}, ArticlesPanierEntity::getNombre)));
	}



	@Override
	public Panier mettreAJourPanier(Panier panier) {
		PanierEntity panierEntity = panierSpringDataRepository.findByNoPanier(panier.getNoPanier()).get();
		
		panier.getArticles().keySet().forEach(article -> {
			
			ArticleEntity entity = articleSpringDataRepository.findByNoArticle(article.getNoArticle()).get();
			
			panierEntity.addArticle(
					elementsPanierSpringDataRepository.save(
							new ArticlesPanierEntity(entity,panier.getArticles().get(article))
					)
			);
		});
		
		panierSpringDataRepository.save(panierEntity);
		
		return Panier.mapPanierFromFields(
				panierEntity.getNoPanier(), 
				panierEntity.getStatus())
				.withArticles(
						panierEntity.getArticles()
							.stream().collect(
									Collectors.toMap(articleEntity -> {
										return Article.mapChampsArticle(
												articleEntity.getArticle().getDescription(), 
												articleEntity.getArticle().getNoArticle(), 
												articleEntity.getArticle().getActif(),
												articleEntity.getArticle().getPrix(),
												new Fabricant(
														articleEntity.getArticle().getFabricant().getId(),
														articleEntity.getArticle().getFabricant().getNom()),
												Stock.creerStock(
														articleEntity.getArticle().getStock().getDescription(), 
														articleEntity.getArticle().getStock().getStock()));
									}, ArticlesPanierEntity::getNombre)));
		
	}

}
