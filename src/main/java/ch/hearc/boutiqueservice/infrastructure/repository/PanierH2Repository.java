package ch.hearc.boutiqueservice.infrastructure.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hearc.boutiqueservice.domaine.exception.PanierInitieExistantException;
import ch.hearc.boutiqueservice.domaine.model.Article;
import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.model.Panier;
import ch.hearc.boutiqueservice.domaine.model.PanierStatus;
import ch.hearc.boutiqueservice.domaine.model.Stock;
import ch.hearc.boutiqueservice.domaine.repository.PanierRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.ArticleSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.ElementsPanierSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.PanierSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.ArticleEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.ElementPanierEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.BiereEntity;
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
		
		if(panierSpringDataRepository.countByStatus(PanierStatus.INITIE) > 0) {
			throw new PanierInitieExistantException("Un seul panier peut-être dans l'état INITIE");
		}
		
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
						panierEntity.getElements()
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
									}, ElementPanierEntity::getNombre)));
	}



	@Override
	public Panier mettreAJourPanier(Panier panier) {
		//Récupération du panier par le numero
		PanierEntity panierEntity = panierSpringDataRepository.findByNoPanier(panier.getNoPanier()).get();
		
		//on vide les éléments
		panierEntity.setArticles(new ArrayList<>());
		
		//Iterations sur les articles
		panier.getArticles().keySet().forEach(article -> {
			
			ArticleEntity entity = articleSpringDataRepository.findByNoArticle(article.getNoArticle()).get();
			
			ElementPanierEntity en = new ElementPanierEntity(entity,panier.getArticles().get(article));
					
			panierEntity.addElement(en);
			
			elementsPanierSpringDataRepository.save(en);
		});
		
		
		panierSpringDataRepository.save(panierEntity);
		
		return Panier.mapPanierFromFields(
				panierEntity.getNoPanier(), 
				panierEntity.getStatus())
				.withArticles(
						panierEntity.getElements()
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
									}, ElementPanierEntity::getNombre)));
		
	}




	@Override
	public List<Panier> listerPaniers() {
		
		List<PanierEntity> paniers = new ArrayList<>();
		
		panierSpringDataRepository.findAll().forEach(paniers::add);
		
		
		return paniers.stream().map(panierEntity -> {
			return creerPanierAgregatFromEntity(panierEntity);
		}).collect(Collectors.toList());
		
	}




	private Panier creerPanierAgregatFromEntity(PanierEntity panierEntity) {
		
		Map<Article, Integer> articlesPanier = new HashMap<>();
		
		panierEntity.getElements().forEach(articlesPanierEntity -> {
			articlesPanier.put(
					Article.mapChampsArticle(
							articlesPanierEntity.getArticle().getDescription(), 
							articlesPanierEntity.getArticle().getNoArticle(), 
							articlesPanierEntity.getArticle().getActif(), 
							articlesPanierEntity.getArticle().getPrix(), 
							new Fabricant(
									articlesPanierEntity.getArticle().getFabricant().getId(),
									articlesPanierEntity.getArticle().getFabricant().getNom()),
							Stock.creerStock(articlesPanierEntity.getArticle().getStock().getDescription(),
									articlesPanierEntity.getArticle().getStock().getStock())
							),
					articlesPanierEntity.getNombre());
		});
		
		return Panier.mapPanierFromFields(panierEntity.getNoPanier(), panierEntity.getStatus()).withArticles(articlesPanier);
		
		
	}

}
