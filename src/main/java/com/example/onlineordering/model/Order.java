package com.example.onlineordering.model;

import java.util.Date;

public class Order {

	private int id;
	private int itemId;
	private int userId;
	private Date orderDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public Order(int id, int itemId, int userId, Date orderDate) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.userId = userId;
		this.orderDate = orderDate;
	}
	
	public Order() {
		
	}
	
	
	
}
