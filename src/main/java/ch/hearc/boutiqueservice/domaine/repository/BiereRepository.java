package ch.hearc.boutiqueservice.domaine.repository;

import java.util.List;

import ch.hearc.boutiqueservice.application.api.web.ressources.BiereRessource;
import ch.hearc.boutiqueservice.domaine.model.Biere;
import ch.hearc.boutiqueservice.domaine.model.TypeBiere;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.TypeBiereEntity;

public interface BiereRepository {

	List<Biere> listerBiere();

	Biere ajouterBiere(Biere biere);
	
	List<TypeBiere> listerTypeBieres();

	TypeBiere getTypeBiereById(String idType);
	
	Biere getBiereByNoArticle(String noArticle);

	
}
