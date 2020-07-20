package com.example.onlineordering.model;

import java.util.Date;

public class Item {
	
	private int id;
	private String name;
	private String description;
	private String imgUrl;
	private double price;
	private int categoryId;
	private Date createdOn;
	
	//constructors
	
	
	//getters and setters

	public Item(int id, String name, String description, String imgUrl, double price, int categoryId, Date createdOn) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
		this.price = price;
		this.categoryId = categoryId;
		this.createdOn = createdOn;
	}
	
	
	public Item() {
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	} 
	
	
}
