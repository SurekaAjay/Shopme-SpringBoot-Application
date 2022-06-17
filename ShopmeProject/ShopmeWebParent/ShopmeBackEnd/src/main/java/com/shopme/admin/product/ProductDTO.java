package com.shopme.admin.product;


//DTO is an object that carries data between processes. When you're working with a remote interface, each call it is expensive. 
//As a result you need to reduce the number of calls.
//The solution is to create a Data Transfer Object that can hold all the data for the call.

public class ProductDTO {
	private String name;
	private String imagePath;
	private float price;
	private float cost;

	public ProductDTO(String name, String imagePath, float price, float cost) {
		this.name = name;
		this.imagePath = imagePath;
		this.price = price;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}