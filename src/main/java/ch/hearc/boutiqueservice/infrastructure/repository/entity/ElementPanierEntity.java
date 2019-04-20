package ch.hearc.boutiqueservice.infrastructure.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "elements_panier")
public class ElementPanierEntity {
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public void setArticle(ArticleEntity article) {
		this.article = article;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private int nombre;
	
	@OneToOne
	private ArticleEntity article;
	
	public ArticleEntity getArticle() {
		return article;
	}
	
	public int getNombre() {
		return nombre;
	}
	
	public ElementPanierEntity() {}

	public ElementPanierEntity(ArticleEntity article, int nombre) {
		super();
		this.article = article;
		this.nombre = nombre;
	}
	
	
	

}
