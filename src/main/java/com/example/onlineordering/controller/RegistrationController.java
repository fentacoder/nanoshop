package com.example.onlineordering.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.onlineordering.daoimpl.AppUserDAOImpl;
import com.example.onlineordering.model.AppUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    @Autowired
    private AppUserDAOImpl userService;

	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("crmUser", new AppUser());
		
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@ModelAttribute("crmUser") AppUser theUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theUser.getUserName();
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "registration-form";
	        }

		// check the database if user already exists
        AppUser existing = userService.findUserAccount(userName);
        if (existing != null){
        	theModel.addAttribute("crmUser", new AppUser());
			theModel.addAttribute("registrationError", "User name already exists.");
        	return "registration-form";
        }
        
        // create user account        						
        userService.save(theUser);
        
        
        return "registration-confirmation";		
	}
}
