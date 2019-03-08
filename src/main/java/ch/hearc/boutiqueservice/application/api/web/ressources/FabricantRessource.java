package ch.hearc.boutiqueservice.application.api.web.ressources;

import ch.hearc.boutiqueservice.domaine.model.Fabricant;

public class FabricantRessource {

	private Long id;
	private String nom;
	
	public FabricantRessource(Long id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}
	
	public static FabricantRessource fromFabricant (Fabricant fabricant) {
		return new FabricantRessource(fabricant.getId(),fabricant.getNom());
	}
}
