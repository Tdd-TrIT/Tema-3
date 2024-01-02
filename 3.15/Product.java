package com.sergiotrapiello.cursotesting.basico;

public final class Product {

	private final String description;
	
	private final double basePrice;
	
	private final double vat;
	
	public Product(String description, double basePrice, int vat) {
		this.description = description;
		this.basePrice = basePrice;
		this.vat = vat;
	}

	public String getDescription() {
		return description;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public double getVat() {
		return vat;
	}

	@Override
	public String toString() {
		return "Product \"" + description + "\"";
	}

}
