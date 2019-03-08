package ch.hearc.boutiqueservice.domaine.service;


import ch.hearc.boutiqueservice.domaine.commande.CreerBiereCommande;
import ch.hearc.boutiqueservice.domaine.model.Article;
import ch.hearc.boutiqueservice.domaine.model.Biere;
import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.model.Stock;
import ch.hearc.boutiqueservice.domaine.model.TypeBiere;
import ch.hearc.boutiqueservice.domaine.repository.BiereRepository;
import ch.hearc.boutiqueservice.domaine.repository.FabricantRepository;
import ch.hearc.boutiqueservice.domaine.repository.StockRepository;

public class BiereDomaineService {

	private final BiereRepository biereRepository;
	private final FabricantRepository fabricantRepository;
	private final StockRepository stockRepository;
	
	public BiereDomaineService(BiereRepository biereRepository, FabricantRepository fabricantRepository,StockRepository stockRepository) {
		this.biereRepository = biereRepository;
		this.fabricantRepository = fabricantRepository;
		this.stockRepository = stockRepository;
	}
	
	public Biere creerBiere (CreerBiereCommande creerBiereCommande) {
		
		Fabricant fabricant = fabricantRepository.getFabricantById(creerBiereCommande.getIdFabricant());
		TypeBiere typeBiere = biereRepository.getTypeBiereById(creerBiereCommande.getIdType());
		
		Stock stock = Stock.creerStock(genererStockDescription(creerBiereCommande), creerBiereCommande.getStockInitial());
		
		Article article = Article.creerArticle(
				genererArticleDescription(creerBiereCommande), 
				creerBiereCommande.getPrix(), 
				fabricant,
				stock);
		
		
		Biere biere = Biere.creerBiere(genererArticleDescription(creerBiereCommande), 
				creerBiereCommande.getPrix(), 
				creerBiereCommande.getContenanceLitre(), 
				typeBiere, 
				article);
		
		biere = biereRepository.ajouterBiere(biere);
		
		return biere;
		
	}

	private String genererStockDescription(CreerBiereCommande creerBiereCommande) {
		return "Stock pour " + creerBiereCommande.getNom();
	}

	private String genererArticleDescription(CreerBiereCommande creerBiereCommande) {
		return "Biere, "+ genererStockDescription(creerBiereCommande);
	}
}
