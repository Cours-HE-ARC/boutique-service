package ch.hearc.boutiqueservice.application.api.web.ressources;

import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.model.TypeBiere;

public class TypeBiereRessource {

	private Long id;
	private String nom;
	
	public TypeBiereRessource(Long id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}
	
	public static TypeBiereRessource fromTypeBiere (TypeBiere typeBiere) {
		return new TypeBiereRessource(typeBiere.getId(),typeBiere.getNom());
	}
	
}
