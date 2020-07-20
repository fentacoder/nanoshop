package com.example.onlineordering.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.onlineordering.daoimpl.AppUserDAOImpl;
import com.example.onlineordering.daoimpl.ItemDAOImpl;
import com.example.onlineordering.model.AppUser;
import com.example.onlineordering.model.Item;
import com.example.onlineordering.utils.WebUtils;


@Controller
public class MainController {
	
	 @Autowired
		private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private ItemDAOImpl itemService;
	
	@Autowired
	private AppUserDAOImpl userService;
	
	@GetMapping("/checkout")
	public String checkout() {
		
		return "checkout";
	}
	
	@GetMapping("/")
	public String showHome(Model model, HttpSession session, Principal principal) {
		
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		if(session.getAttribute("user")==null){
		session.setAttribute("user",loginedUser);
		}
		
		List<Item> myList=itemService.getAllItems();
		model.addAttribute("items", myList);
	///uploads/photo 3x4.jpg
        return "index";
	}
	
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage(Model model) {
		model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
	}
	
	
	@RequestMapping("/admin")
    public String adminPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
         
        return "adminPage";
    }
	
	@RequestMapping("/login")
    public String loginPage(Model model) {
 
        return "loginPage";
    }
 
    @RequestMapping("/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping("/userInfo")
    public String userInfo(Model model, Principal principal) {
 
        // (1) (en)
        // After user login successfully.
        // (vi)
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();
        AppUser user=userService.findUserAccount(userName);
        model.addAttribute("setuser", user);
        return "userInfo";
    }
    
    @PostMapping("userInfo/save")
	public String saveEmployee(@ModelAttribute("setuser") AppUser theuser) {
		
		userService.update(theuser);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/";
	}
    
    @GetMapping("userInfo/delete")
	public String delete(@RequestParam("usid") int theId) {
		
		
		userService.disableUser(theId);
		
		
		return "redirect:/login";
		
	}
 
    @RequestMapping("/403")
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
            model.addAttribute("userInfo", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "403Page";
    }
}