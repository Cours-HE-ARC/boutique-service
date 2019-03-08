package ch.hearc.boutiqueservice.application.api.web.ressources;

import java.math.BigDecimal;

import ch.hearc.boutiqueservice.domaine.model.Biere;

public class BiereRessource {

	private String nom;
	private String fabricant;
	private BigDecimal prix;
	private String type;
	private int stock;
	private BigDecimal contenanceL;
	private String noArticle;
	
	
	public BigDecimal getContenanceL() {
		return contenanceL;
	}

	public String getNom() {
		return nom;
	}

	public String getFabricant() {
		return fabricant;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public int getStock() {
		return stock;
	}

	public BiereRessource(String noArticle,String nom, String type, String fabricant, BigDecimal prix, int stock, BigDecimal contenanceL) {
		super();
		this.noArticle = noArticle;
		this.nom = nom;
		this.fabricant = fabricant;
		this.prix = prix;
		this.stock = stock;
		this.type = type;
		this.contenanceL = contenanceL;
	}

	public static BiereRessource fromBiere(Biere b) {
		
		return new BiereRessource(
				b.getArticle().getNoArticle(),
				b.getNom(), 
				b.getType().getNom(),
				b.getArticle().getFabricant().getNom(),
				b.getArticle().getPrix(), 
				b.getArticle().getStock().getStock(),
				b.getContenanceL());
	}

	public String getNoArticle() {
		return noArticle;
	}

	public String getType() {
		return type;
	}
	
}
