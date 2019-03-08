package ch.hearc.boutiqueservice.application.api.web.ressources;

import ch.hearc.boutiqueservice.domaine.model.Panier;

public class CreerPanierReponseResource {

	private String noPanier;
	
	public static CreerPanierReponseResource fromPanier(Panier panier) {
		return new CreerPanierReponseResource(panier.getNoPanier());
	}

	public String getNoPanier() {
		return noPanier;
	}

	public CreerPanierReponseResource(String noPanier) {
		this.noPanier = noPanier;
	}

}
