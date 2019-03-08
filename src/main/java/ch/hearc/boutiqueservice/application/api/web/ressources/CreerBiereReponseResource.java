package ch.hearc.boutiqueservice.application.api.web.ressources;

import ch.hearc.boutiqueservice.domaine.model.Biere;

public class CreerBiereReponseResource {

	private String noArticle;

	public CreerBiereReponseResource(String noArticle) {
		this.noArticle = noArticle;
	}

	public String getNoArticle() {
		return noArticle;
	}

	public static CreerBiereReponseResource fromBiere(Biere biere) {
		
		CreerBiereReponseResource creerBiereReponseResource = new CreerBiereReponseResource(biere.getArticle().getNoArticle());
		
		return creerBiereReponseResource;
	}
	
	
}
