package ch.hearc.boutiqueservice.domaine.model;

public class TypeBiere {
	
	private String nom;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public TypeBiere(Long id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	
	

}
