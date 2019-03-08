package ch.hearc.boutiqueservice.domaine.commande;

public class AjouterArticlePanierCommande {

	private String noArticle;
	private int nombre;
	
	@Override
	public String toString() {
		return "AjouterArticlePanierCommande [noArticle=" + noArticle + ", nombre=" + nombre + "]";
	}
	public String getNoArticle() {
		return noArticle;
	}
	public int getNombre() {
		return nombre;
	}
	
	
}
