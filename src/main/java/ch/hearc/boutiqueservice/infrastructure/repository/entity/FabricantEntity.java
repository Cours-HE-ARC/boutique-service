package ch.hearc.boutiqueservice.infrastructure.repository.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ch.hearc.boutiqueservice.domaine.model.Fabricant;

@Entity
@Table(name = "fabricant")
public class FabricantEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom")
	private String nom;
	
	//@OneToMany(mappedBy="id")
    //private Set<ArticleEntity> articles;
	
	public FabricantEntity(Fabricant fabricant) {
		this.nom = fabricant.getNom();
	}
	
	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	FabricantEntity() {
	}

	public Fabricant toFabricant() {
		return new Fabricant(id, nom);
	}
	
}
