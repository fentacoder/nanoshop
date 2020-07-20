package com.example.onlineordering.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineordering.daoimpl.AppUserDAOImpl;
import com.example.onlineordering.model.AppUser;

@RestController
@RequestMapping("/api")
public class AppUserController {

	@Autowired
	AppUserDAOImpl appUserService;
	
	
	@GetMapping("/users")
	public List<AppUser> getUsers() {
		
		return appUserService.getAllUsers();
		
	}
}
