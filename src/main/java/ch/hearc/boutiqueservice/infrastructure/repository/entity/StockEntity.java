package ch.hearc.boutiqueservice.infrastructure.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ch.hearc.boutiqueservice.domaine.model.Stock;

@Entity
@Table(name = "stock_article")
public class StockEntity {

	
	
	StockEntity() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "description")
	private String description;
	
	public StockEntity(Stock stock) {
		this.stock = stock.getStock();
		this.description = stock.getDescription();
		
	}

	public Long getId() {
		return id;
	}
	
	public Stock toStock() {
		return Stock.creerStock(description, stock);
	}

	public int getStock() {
		return stock;
	}

	public String getDescription() {
		return description;
	}

	
}
