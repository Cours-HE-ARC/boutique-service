
package ch.hearc.boutiqueservice.domaine.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Panier {

	private Map<Article, Integer> articles;
	private PanierStatus status;
	private String noPanier;
	
	public static Panier creerPanier() {
		Panier panier = new Panier();
		panier.articles = new HashMap<>();
		panier.status = PanierStatus.INITIE;
		panier.noPanier = UUID.randomUUID().toString();
		return panier;
	}
	
	public static Panier mapPanierFromFields(String noPanier, PanierStatus status) {
		Panier panier = new Panier();
		panier.articles = new HashMap<>();
		panier.status = status;
		panier.noPanier = noPanier;
		return panier;
	}
	
	
	public Panier withArticles(Map<Article,Integer> articles) {
		this.articles = articles;
		return this;
	}
	
	public Map<Article, Integer> getArticles() {
		return articles;
	}

	public PanierStatus getStatus() {
		return status;
	}

	public String getNoPanier() {
		return noPanier;
	}

	public boolean ajouterArticle(Article article, int nombre) {
		
		if(status != PanierStatus.VALIDE) {
			
			articles.put(article, nombre);
			
			if(status == PanierStatus.INITIE) {
				status = PanierStatus.PENDING;
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean enleverArticle(Article article) {
		
		if(status != PanierStatus.VALIDE) {
			
			if(articles.containsKey(article)) {
				articles.remove(article);
				return true;
			}
		}
		
		return false;
	}
	
	
}
