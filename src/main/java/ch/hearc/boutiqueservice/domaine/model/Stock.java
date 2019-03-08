package ch.hearc.boutiqueservice.domaine.model;

public class Stock {

	
	private int stock;
	private String description;
	

	private Stock(String description,int stock) {
		super();
		this.description = description;
		this.stock = stock;
	}


	public int getStock() {
		return stock;
	}



	public static Stock creerStock(String description, int stock) {
		return new Stock(description, stock);
	}


	public String getDescription() {
		return description;
	}
}
