package com.example.onlineordering.dao;

import java.util.List;

import com.example.onlineordering.model.Item;

public interface ItemDAO {
	
	List<Item> getAllItems();
	void save(Item item);
	void delete (Item item);
}
