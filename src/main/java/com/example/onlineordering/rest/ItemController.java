package com.example.onlineordering.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineordering.daoimpl.ItemDAOImpl;
import com.example.onlineordering.model.Item;

@RestController
@RequestMapping("/api")
public class ItemController {

	@Autowired
	ItemDAOImpl itemService;
	
	
	@GetMapping("/items")
	public List<Item> getItems() {
		
		return itemService.getAllItems();
		
	}
}

