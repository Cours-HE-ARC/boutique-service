package ch.hearc.boutiqueservice.application.service;

import ch.hearc.boutiqueservice.domaine.commande.AjouterArticlePanierCommande;
import ch.hearc.boutiqueservice.domaine.commande.CreerPanierCommande;
import ch.hearc.boutiqueservice.domaine.model.Biere;
import ch.hearc.boutiqueservice.domaine.model.Panier;

public interface PanierService {

	Panier creerPanier(CreerPanierCommande creerPanierCommande);

	Panier ajouterArticlePanier(String noPanier, AjouterArticlePanierCommande ajouterArticlePanierCommande);

	Panier getPanierByNoPanier(String noPanier);
}
