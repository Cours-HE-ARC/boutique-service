package ch.hearc.boutiqueservice.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.boutiqueservice.application.service.PanierService;
import ch.hearc.boutiqueservice.domaine.commande.AjouterArticlePanierCommande;
import ch.hearc.boutiqueservice.domaine.commande.CreerPanierCommande;
import ch.hearc.boutiqueservice.domaine.model.Panier;
import ch.hearc.boutiqueservice.domaine.repository.ArticleRepository;
import ch.hearc.boutiqueservice.domaine.repository.PanierRepository;
import ch.hearc.boutiqueservice.domaine.service.PanierDomaineService;

@Service
public class PanierServiceImpl implements PanierService{

	
	private PanierRepository panierRepository;
	private ArticleRepository articleRepository;
	private PanierDomaineService panierDomaineService;
	
	
	
	@Autowired
	public PanierServiceImpl(PanierRepository panierRepository,ArticleRepository articleRepository) {
		super();
		this.panierRepository = panierRepository;
		this.articleRepository = articleRepository;
		this.panierDomaineService = new PanierDomaineService(panierRepository,articleRepository);
	}



	@Override
	public Panier creerPanier(CreerPanierCommande creerPanierCommande) {
		return panierDomaineService.creerPanier(creerPanierCommande);
	}



	@Override
	public Panier ajouterArticlePanier(String noPanier,AjouterArticlePanierCommande ajouterArticlePanierCommande) {
		return panierDomaineService.ajouterArticle(noPanier,ajouterArticlePanierCommande);
	}



	@Override
	public Panier getPanierByNoPanier(String noPanier) {
		return panierRepository.getPanierByNoPanier(noPanier);
	}
	
	
	
}
