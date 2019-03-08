package ch.hearc.boutiqueservice.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.boutiqueservice.application.api.web.ressources.BiereRessource;
import ch.hearc.boutiqueservice.application.service.BiereService;
import ch.hearc.boutiqueservice.domaine.commande.CreerBiereCommande;
import ch.hearc.boutiqueservice.domaine.model.Biere;
import ch.hearc.boutiqueservice.domaine.model.TypeBiere;
import ch.hearc.boutiqueservice.domaine.repository.BiereRepository;
import ch.hearc.boutiqueservice.domaine.repository.FabricantRepository;
import ch.hearc.boutiqueservice.domaine.repository.StockRepository;
import ch.hearc.boutiqueservice.domaine.service.BiereDomaineService;

@Service
public class BiereServiceImpl implements BiereService {

	
	private BiereRepository biereRepository;
	private FabricantRepository fabricantRepository;
	private BiereDomaineService biereDomaineService;
	private StockRepository stockRepository;
	
	
	@Autowired
	public BiereServiceImpl(BiereRepository biereRepository,FabricantRepository fabricantRepository,StockRepository stockRepository) {
		this.biereRepository = biereRepository;
		this.fabricantRepository = fabricantRepository;
		this.stockRepository = stockRepository;
		this.biereDomaineService = new BiereDomaineService(biereRepository, fabricantRepository, stockRepository);
	}


/**
 * 
 */
	public List<Biere> getAllBieres() {
		
		return biereRepository.listerBiere();
	
	}
	
	@Override
	public List<TypeBiere> getAllTypeBieres() {
		
		return biereRepository.listerTypeBieres();
	
	}
	
	public Biere creerBiere(CreerBiereCommande creerBiereCommande) {
		
		return biereDomaineService.creerBiere(creerBiereCommande);
	}


	@Override
	public Biere getBiereByNoArticle(String noArticle) {
		return biereRepository.getBiereByNoArticle(noArticle);
	}

	
}
