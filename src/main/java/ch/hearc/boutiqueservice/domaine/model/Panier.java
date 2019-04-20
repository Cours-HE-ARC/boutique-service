
package ch.hearc.boutiqueservice.domaine.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
	
	public boolean contientArticle(String noArticle) {
		Optional<String> noArticlePresent = this.articles.keySet().stream()
			.map(article -> 
				article.getNoArticle()
			).filter(articlelNo -> articlelNo.equals(noArticle))
			.findFirst();
		
		return noArticlePresent.isPresent();
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
		
		//ajout article seulement si etat initite
		if(status == PanierStatus.INITIE) {
			
			if(this.contientArticle(article.getNoArticle())) {
				articles.computeIfPresent(article, (cle,valeur) -> {
					int nombreCommandes = valeur + nombre;
					return nombreCommandes;
				});
			}else {
				articles.put(article, nombre);
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
