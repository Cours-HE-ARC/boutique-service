package ch.hearc.boutiqueservice.application.service;

import java.util.List;

import ch.hearc.boutiqueservice.domaine.commande.CreerBiereCommande;
import ch.hearc.boutiqueservice.domaine.commande.CreerPanierCommande;
import ch.hearc.boutiqueservice.domaine.model.Biere;
import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.model.Panier;

public interface BoutiqueService {

	List<Fabricant> getAllFabricants();
	
}
