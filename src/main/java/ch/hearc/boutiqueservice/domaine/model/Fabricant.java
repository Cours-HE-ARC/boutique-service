package ch.hearc.boutiqueservice.domaine.model;

public class Fabricant {

	private String nom;

	private Long id;
	
	public Fabricant(Long id,String nom) {
		super();
		this.nom = nom;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}
	
	
}
