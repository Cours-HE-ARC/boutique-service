package ch.hearc.boutiqueservice.application.api.web.ressources;

import java.util.Map;

import ch.hearc.boutiqueservice.domaine.model.Article;
import ch.hearc.boutiqueservice.domaine.model.Panier;
import ch.hearc.boutiqueservice.domaine.model.PanierStatus;

public class PanierRessource {

	private Map<Article, Integer> articles;
	private PanierStatus status;
	private String noPanier;
	
	
	public PanierRessource(PanierStatus status, String noPanier, Map<Article, Integer> articles) {
		this.status = status;
		this.noPanier = noPanier;
		this.articles = articles;
	}

	public static PanierRessource fromPanier(Panier panier) {
		return new PanierRessource(panier.getStatus(),panier.getNoPanier(),panier.getArticles());
	}
	
	public Map<Article, Integer> getArticles() {
		return articles;
	}
	public void setArticles(Map<Article, Integer> articles) {
		this.articles = articles;
	}
	public PanierStatus getStatus() {
		return status;
	}
	public void setStatus(PanierStatus status) {
		this.status = status;
	}
	public String getNoPanier() {
		return noPanier;
	}
	public void setNoPanier(String noPanier) {
		this.noPanier = noPanier;
	}
	
	
}
