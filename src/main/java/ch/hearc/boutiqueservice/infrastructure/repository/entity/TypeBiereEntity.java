package ch.hearc.boutiqueservice.infrastructure.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ch.hearc.boutiqueservice.domaine.model.TypeBiere;

@Entity
@Table(name = "type_biere")
public class TypeBiereEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	TypeBiereEntity() {}
	
	public TypeBiereEntity(String nom) {
		this.nom = nom;
	}


	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "nom")
	private String nom;
	
	public TypeBiere toTypeBiere() {
		return new TypeBiere(id,nom);
	}
}
