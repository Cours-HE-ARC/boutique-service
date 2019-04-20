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

	public void setId(Long id) {
		this.id = id;
	}

	public void setNoPanier(String noPanier) {
		this.noPanier = noPanier;
	}

	public void setStatus(PanierStatus status) {
		this.status = status;
	}

	public void setArticles(List<ElementPanierEntity> articles) {
		this.elements = articles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noPanier == null) ? 0 : noPanier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PanierEntity other = (PanierEntity) obj;
		if (noPanier == null) {
			if (other.noPanier != null)
				return false;
		} else if (!noPanier.equals(other.noPanier))
			return false;
		return true;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "noPanier")
	private String noPanier;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private PanierStatus status;
	
	@OneToMany
	private List<ElementPanierEntity> elements;
	
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

	public List<ElementPanierEntity> getElements() {
		return elements;
	}
	
	public void addElement(ElementPanierEntity articleEntity) {
		
		if(this.elements.contains(articleEntity)) {
			ElementPanierEntity articlePresent = this.elements.stream().filter(article -> article.getArticle().getNoArticle().equals(articleEntity.getArticle().getNoArticle())).findFirst().get();
			articlePresent.setNombre(articlePresent.getNombre() + articleEntity.getNombre());
			
		}else {
			this.elements.add(articleEntity);
		}
		
		
	}
	
	
	
}
