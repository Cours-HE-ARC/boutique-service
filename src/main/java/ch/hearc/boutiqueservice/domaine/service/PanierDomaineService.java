package ch.hearc.boutiqueservice.domaine.service;

import ch.hearc.boutiqueservice.domaine.commande.AjouterArticlePanierCommande;
import ch.hearc.boutiqueservice.domaine.commande.CreerPanierCommande;
import ch.hearc.boutiqueservice.domaine.model.Article;
import ch.hearc.boutiqueservice.domaine.model.Panier;
import ch.hearc.boutiqueservice.domaine.repository.ArticleRepository;
import ch.hearc.boutiqueservice.domaine.repository.PanierRepository;

public class PanierDomaineService {

	private PanierRepository panierRepository;
	private ArticleRepository articleRepository;
	
	
	
	
	public PanierDomaineService(PanierRepository panierRepository, ArticleRepository articleRepository) {
		this.panierRepository = panierRepository;
		this.articleRepository = articleRepository;
	}



	public Panier creerPanier(CreerPanierCommande creerPanierCommande) {
		
		Panier panier = Panier.creerPanier();
		
		return panierRepository.creerPanier(panier);
	}



	public Panier ajouterArticle(String noPanier, AjouterArticlePanierCommande ajouterArticlePanierCommande) {
		
		Panier panier = panierRepository.getPanierByNoPanier(noPanier);
		
		Article article = articleRepository.getArticleByNoArticle(ajouterArticlePanierCommande.getNoArticle());
		
		panier.ajouterArticle(article, ajouterArticlePanierCommande.getNombre());
		
		return panierRepository.mettreAJourPanier(panier);
	
		
	}

}
