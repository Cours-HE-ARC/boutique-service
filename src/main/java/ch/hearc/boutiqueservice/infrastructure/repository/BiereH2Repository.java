package ch.hearc.boutiqueservice.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hearc.boutiqueservice.application.api.web.ressources.BiereRessource;
import ch.hearc.boutiqueservice.domaine.model.Article;
import ch.hearc.boutiqueservice.domaine.model.Biere;
import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.model.Stock;
import ch.hearc.boutiqueservice.domaine.model.TypeBiere;
import ch.hearc.boutiqueservice.domaine.repository.BiereRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.ArticleSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.BiereSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.FabricantSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.StockSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.TypeBiereSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.ArticleEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.BiereEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.FabricantEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.StockEntity;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.TypeBiereEntity;

@Repository
public class BiereH2Repository implements BiereRepository {

	@Autowired
	private BiereSpringDataRepository biereJpaRepository;
	
	@Autowired
	private TypeBiereSpringDataRepository typeBiereRepository;
	
	@Autowired
	private FabricantSpringDataRepository fabricantSpringDataRepository;
	
	@Autowired
	private ArticleSpringDataRepository articleSpringDataRepository;
	
	@Autowired
	private StockSpringDataRepository stockSpringDataRepository;
	
	
	


	public Biere ajouterBiere(Biere biere) {
		
		//récupératio des fabricant et type passé en param avec id
		FabricantEntity fabricantEntity = fabricantSpringDataRepository.findById(biere.getArticle().getFabricant().getId()).get();
		TypeBiereEntity typeBiere = typeBiereRepository.findById(biere.getType().getId()).get();
	
		
		StockEntity stockEntity = stockSpringDataRepository.save(new StockEntity(biere.getArticle().getStock()));
		
		ArticleEntity articleEntity = articleSpringDataRepository.save(
				new ArticleEntity(biere.getArticle(), stockEntity, fabricantEntity)
		);
		
		
		BiereEntity biereEntity = new BiereEntity(
				articleEntity,
				typeBiere,
				biere.getNom(),
				biere.getContenanceL());
		
		this.biereJpaRepository.save(biereEntity);
		
		return creerBiereAgregatFromEntity(biereEntity);
	}

	private Biere creerBiereAgregatFromEntity(BiereEntity biereEntity) {
		
		return Biere.creerBiere(
				biereEntity.getNom(), 
				biereEntity.getArticle().getPrix(), 
				biereEntity.getContenanceLitre(), 
				new TypeBiere(biereEntity.getType().getId(),biereEntity.getType().getNom()),
				Article.mapChampsArticle(
						biereEntity.getArticle().getDescription(),
						biereEntity.getArticle().getNoArticle(), 
						biereEntity.getArticle().getActif(),
						biereEntity.getArticle().getPrix(), 
						new Fabricant(biereEntity.getArticle().getFabricant().getId(),
								biereEntity.getArticle().getFabricant().getNom()),
						Stock.creerStock(biereEntity.getArticle().getStock().getDescription(),biereEntity.getArticle().getStock().getStock()))
				);
	}
	
	public List<Biere> listerBiere() {
		
		List<BiereEntity> bieres = new ArrayList<BiereEntity>();
		
		Iterable<BiereEntity> it = biereJpaRepository.findAll();
		
		it.forEach(b -> {
			System.out.println(b);
		});
		
		biereJpaRepository.findAll().forEach(bieres::add);
		
		
		return bieres.stream().map(biereEntity -> {
			return creerBiereAgregatFromEntity(biereEntity);
		}).collect(Collectors.toList());
		
		
	}

	@Override
	public List<TypeBiere> listerTypeBieres() {
		 
		List<TypeBiereEntity> typesBieres = new ArrayList<>();
		
		typeBiereRepository.findAll().forEach(typesBieres::add);
		
		return typesBieres.stream().map(entity -> entity.toTypeBiere()).collect(Collectors.toList());
		
		
	}

	@Override
	public TypeBiere getTypeBiereById(String idType) {
		 return typeBiereRepository.findById(Long.valueOf(idType)).get().toTypeBiere();
	}

	public Biere getBiereByNoArticle (String noArticle) {
		
		BiereEntity biereEntity =  biereJpaRepository.findByArticle_NoArticle(noArticle).get();
		
		return creerBiereAgregatFromEntity(biereEntity);
		
	}

}
