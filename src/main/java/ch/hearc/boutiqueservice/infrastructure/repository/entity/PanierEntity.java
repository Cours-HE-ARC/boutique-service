package ch.hearc.boutiqueservice.infrastructure.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ch.hearc.boutiqueservice.domaine.model.Article;
import ch.hearc.boutiqueservice.domaine.model.PanierStatus;

@Entity
@Table(name = "panier")
public class PanierEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "noPanier")
	private String noPanier;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private PanierStatus status;
	
	@OneToMany
	private List<ArticlesPanierEntity> articles;
	
	PanierEntity () {}
	
	public PanierEntity(String noPanier, PanierStatus status) {
		this.noPanier = noPanier;
		this.status = status;
	}

	public String getNoPanier() {
		return noPanier;
	}

	public PanierStatus getStatus() {
		return status;
	}

	public Long getId() {
		return id;
	}

	public List<ArticlesPanierEntity> getArticles() {
		return articles;
	}
	
	public void addArticle(ArticlesPanierEntity articleEntity) {
		this.articles.add(articleEntity);
	}
	
	
	
}
