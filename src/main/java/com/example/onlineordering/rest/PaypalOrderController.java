package com.example.onlineordering.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineordering.daoimpl.ItemDAOImpl;
import com.example.onlineordering.daoimpl.PaypalOrderDAOImpl;
import com.example.onlineordering.model.Item;
import com.example.onlineordering.model.Order;

@RestController
@RequestMapping("/api")
public class PaypalOrderController {

	@Autowired
	PaypalOrderDAOImpl orderService;
	
	@GetMapping("/orders")
	public List<Order> getOrders() {
		
		return orderService.getAllOrders();
		
	}
	
	@GetMapping("/orders/{orderId}")
	public Order getOrder(@PathVariable int orderId) {
		
		Order theOrder = orderService.getOrder(orderId);
		
		if (theOrder == null) {
			System.out.println("Order did not found");
		}
		
		return theOrder;
	}

	@PostMapping("/orders")
	public Order addOrder(@RequestBody Order theOrder) {
		
		// also just in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update
		
		theOrder.setId(0);
		
		orderService.save(theOrder);
		
		return theOrder;
	}

	@DeleteMapping("/orders/{orderId}")
	public String deleteOrder(@PathVariable int orderId) {
		
		/* Order tempOrder = orderService.getOrder(orderId);
		
		// throw exception if null
		
		if (tempOrder == null) {
			System.out.println("Order did not found");
		}*/
				
		orderService.delete(orderId);
		
		return "Deleted order id - " + orderId;
	}

}