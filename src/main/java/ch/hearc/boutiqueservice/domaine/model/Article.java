package ch.hearc.boutiqueservice.domaine.model;

import java.math.BigDecimal;
import java.util.UUID;

public  class Article {

	@Override
	public String toString() {
		return "Article [prix=" + prix + ", noArticle=" + noArticle + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noArticle == null) ? 0 : noArticle.hashCode());
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
		Article other = (Article) obj;
		if (noArticle == null) {
			if (other.noArticle != null)
				return false;
		} else if (!noArticle.equals(other.noArticle))
			return false;
		return true;
	}

	private BigDecimal prix;
	private String noArticle;
	private Boolean actif;
	private Fabricant fabricant;
	private String description;
	private Stock stock;
	
	public static Article creerArticle(String description,BigDecimal prix, Fabricant fabricant, Stock stock) {
		return new Article(description, prix, fabricant,stock);
	}
	
	public static Article mapChampsArticle(String description,String noArticle, Boolean actif, BigDecimal prix, Fabricant fabricant, Stock stock) {
		Article article =  new Article(description, prix, fabricant,stock);
		article.noArticle = noArticle;
		article.actif = actif;
		return article;
	}
	
	
	private Article(String description,BigDecimal prix, Fabricant fabricant, Stock stock) {
		this.prix = prix;
		this.noArticle = UUID.randomUUID().toString();
		this.actif = Boolean.TRUE;
		this.fabricant = fabricant;
		this.description = description;
		this.stock = stock;
	}
	
	public Stock getStock() {
		return stock;
	}

	
	
	public String getDescription() {
		return description;
	}

	public Fabricant getFabricant() {
		return fabricant;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public String getNoArticle() {
		return noArticle;
	}

	public Boolean getActif() {
		return actif;
	}
	
	
	
}
