package com.example.onlineordering.dao;

import java.util.List;

import com.example.onlineordering.model.Order;

public interface StripeOrderDAO {
	
	List<Order> getAllOrders();
	Order getOrder(int id);
	void save(Order order);
	void delete (int id);
}
