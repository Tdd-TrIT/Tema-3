package com.sergiotrapiello.cursotesting.basico;

public final class InvoiceLine {

	private final String productDescription;
	
	private final double basePrice;
	
	private final double appliedVat;
	
	private final double totalPrice;

	private final int units;
	
	public InvoiceLine(Product product, int units) {
		if(product == null) {
			throw new IllegalArgumentException("product cannot be null");
		}
		
		this.productDescription = product.getDescription();
		this.basePrice = product.getBasePrice();
		this.appliedVat = product.getVat();
		this.units = units;
		this.totalPrice = calculateTotalPrice();
	}

	private double calculateTotalPrice() {
		return basePrice * (1 + appliedVat / 100) * this.units;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public double getAppliedVat() {
		return appliedVat;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

}
