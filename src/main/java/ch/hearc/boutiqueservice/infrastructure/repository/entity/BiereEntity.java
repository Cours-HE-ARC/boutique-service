package ch.hearc.boutiqueservice.infrastructure.repository.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ch.hearc.boutiqueservice.domaine.model.Article;
import ch.hearc.boutiqueservice.domaine.model.Biere;
import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.model.TypeBiere;

@Entity
@Table(name = "biere")
@PrimaryKeyJoinColumn(name = "id")
public class BiereEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private ArticleEntity article;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "contenance_l")
	private BigDecimal contenanceLitre;
	
	@ManyToOne
    @JoinColumn(name="type_id", nullable=false)
    private TypeBiereEntity type;
	
	BiereEntity() {}

	public BiereEntity(ArticleEntity article, TypeBiereEntity type,String nomBiere, BigDecimal contenanceLitre) {
		this.article = article;
		this.type = type;
		this.nom = nomBiere;
		this.contenanceLitre = contenanceLitre;
	}

	public String getNom() {
		return nom;
	}

	public BigDecimal getContenanceLitre() {
		return contenanceLitre;
	}

	

	public TypeBiereEntity getType() {
		return type;
	}

	public ArticleEntity getArticle() {
		return article;
	}

	public BiereEntity fromBiere(Biere biere) {
		
		BiereEntity bEntity = new BiereEntity(article,type,biere.getNom(),biere.getContenanceL());
		
		return bEntity;
	}
}
