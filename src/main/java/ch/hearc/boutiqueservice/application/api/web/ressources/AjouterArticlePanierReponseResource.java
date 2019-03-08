package ch.hearc.boutiqueservice.application.api.web.ressources;

import ch.hearc.boutiqueservice.domaine.model.Panier;

public class AjouterArticlePanierReponseResource {

	
	private String noPanier;
	

	public AjouterArticlePanierReponseResource(String noPanier) {
		this.noPanier = noPanier;
	}



	public String getNoPanier() {
		return noPanier;
	}



	public static AjouterArticlePanierReponseResource fromPanier(Panier ajouterArticlePanier) {
		return new AjouterArticlePanierReponseResource(ajouterArticlePanier.getNoPanier());
	}

}
