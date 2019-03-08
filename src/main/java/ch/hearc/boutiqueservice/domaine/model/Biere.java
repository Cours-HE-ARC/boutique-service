package ch.hearc.boutiqueservice.domaine.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;


public class Biere{

	@Override
	public String toString() {
		return "Biere [identifiant=" + identifiant + ", nom=" + nom + ", type=" + type + ", contenanceL=" + contenanceL
				+ "]";
	}

	public String getNom() {
		return nom;
	}

	public TypeBiere getType() {
		return type;
	}

	

	
	public String getIdentifiant() {
		return identifiant;
	}

	private String identifiant;
	private String nom;
	private TypeBiere type;
	private BigDecimal contenanceL;
	private Article article;
	

	public BigDecimal getContenanceL() {
		return contenanceL;
	}

	
	private Biere(String nom, BigDecimal prix, BigDecimal contenanceL,TypeBiere typeBiere, Article article) {
		
		this.nom = nom;
		this.type = typeBiere;
		this.identifiant = UUID.randomUUID().toString();
		this.contenanceL = contenanceL;
		this.article = article;
	}

	private static void valideParametres(String nom, TypeBiere type) {
		
		Objects.requireNonNull(nom);
		Objects.requireNonNull(type);
		
		if(nom.isEmpty() || nom.length() < 5) {
			throw new IllegalArgumentException("Le nom de la biere doit comporter au moins 5 caract�res");
		}
	}

	public static Biere creerBiere(String nom, BigDecimal prix, BigDecimal contenanceL, TypeBiere typeBiere, Article article) {
		
		Biere bierre = new Biere(nom, prix, contenanceL, typeBiere, article);
		System.out.println(bierre);
		return bierre;
		
	}
	
	public static Biere mapBiereFields(String nom, BigDecimal prix, BigDecimal contenanceL, TypeBiere typeBiere,  Article article) {
		
		Biere bierre = new Biere(nom, prix, contenanceL, typeBiere, article);
		System.out.println(bierre);
		return bierre;
		
	}

	public Article getArticle() {
		return article;
	}
}
