package ch.hearc.boutiqueservice.domaine.commande;

import java.math.BigDecimal;

import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.model.TypeBiere;

/**
 * <p>
 * 	Commande reflétant une intention
 * </p>
 * <p>
 * 	Utilisé comme point d'entrée pour la création de bière. 
 * </p>
 * @author seb
 *
 */
public class CreerBiereCommande {

	
	@Override
	public String toString() {
		return "CreerBiereCommande [nom=" + nom + ", idType=" + idType + ", idFabricant=" + idFabricant
				+ ", contenanceLitre=" + contenanceLitre + ", prix=" + prix + ", stockInitial=" + stockInitial + "]";
	}
	
	private String nom;
	private String idType;
	private String idFabricant;
	private BigDecimal contenanceLitre;
	private BigDecimal prix;
	private Integer stockInitial;
	
	
	public Integer getStockInitial() {
		return stockInitial;
	}
	public BigDecimal getPrix() {
		return prix;
	}
	public BigDecimal getContenanceLitre() {
		return contenanceLitre;
	}
	public String getNom() {
		return nom;
	}
	public String getIdType() {
		return idType;
	}
	public String getIdFabricant() {
		return idFabricant;
	}
	
	
}
