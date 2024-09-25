package com.yash.vijayvegmart.model;

import java.sql.Timestamp;

public class VegetablesDetails {
    
    private int vegId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int vendorId;
    private String vegName;
    private int quantity;
    private String description;
    private double pricePerPiece;
    private String vegPicName;
    private String vegCategory;
    
	public VegetablesDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public VegetablesDetails(int vendorId, String vegName, int quantity,
			String description, double pricePerPiece, String vegPicName, String vegCategory) {
		super();

		this.vendorId = vendorId;
		this.vegName = vegName;
		this.quantity = quantity;
		this.description = description;
		this.pricePerPiece = pricePerPiece;
		this.vegPicName = vegPicName;
		this.vegCategory = vegCategory;
	}


	public int getVegId() {
		return vegId;
	}


	public void setVegId(int vegId) {
		this.vegId = vegId;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	public Timestamp getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}


	public int getVendorId() {
		return vendorId;
	}


	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}


	public String getVegName() {
		return vegName;
	}


	public void setVegName(String vegName) {
		this.vegName = vegName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPricePerPiece() {
		return pricePerPiece;
	}


	public void setPricePerPiece(double pricePerPiece) {
		this.pricePerPiece = pricePerPiece;
	}


	public String getVegPicName() {
		return vegPicName;
	}


	public void setVegPicName(String vegPicName) {
		this.vegPicName = vegPicName;
	}


	public String getVegCategory() {
		return vegCategory;
	}


	public void setVegCategory(String vegCategory) {
		this.vegCategory = vegCategory;
	}
    
    

}

   