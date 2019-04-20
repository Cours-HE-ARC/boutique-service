package ch.hearc.boutiqueservice.domaine.repository;

import java.util.List;

import ch.hearc.boutiqueservice.domaine.model.Panier;

public interface PanierRepository {

	Panier creerPanier(Panier panier);

	Panier getPanierByNoPanier(String noPanier);

	Panier mettreAJourPanier(Panier panier);

	List<Panier> listerPaniers();
}
