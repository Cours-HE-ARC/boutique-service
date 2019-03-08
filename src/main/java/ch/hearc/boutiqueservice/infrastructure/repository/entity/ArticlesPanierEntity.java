package ch.hearc.boutiqueservice.infrastructure.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articles_panier")
public class ArticlesPanierEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private int nombre;
	
	@OneToOne(fetch = FetchType.LAZY)
	private ArticleEntity article;
	
	public ArticleEntity getArticle() {
		return article;
	}
	
	public int getNombre() {
		return nombre;
	}
	
	public ArticlesPanierEntity() {}

	public ArticlesPanierEntity(ArticleEntity article, int nombre) {
		super();
		this.article = article;
		this.nombre = nombre;
	}
	
	
	

}
