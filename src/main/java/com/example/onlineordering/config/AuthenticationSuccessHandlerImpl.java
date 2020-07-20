package com.example.onlineordering.config;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.onlineordering.daoimpl.AppUserDAOImpl;
import com.example.onlineordering.model.AppUser;


@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{

    @Autowired HttpSession session; //autowiring session

    private AppUserDAOImpl appUserDAO;


    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        String userName = "";
        if(authentication.getPrincipal() instanceof Principal) {
             userName = ((Principal)authentication.getPrincipal()).getName();

        }else {
            userName = ((User)authentication.getPrincipal()).getUsername();
        }
        logger.info("userName: " + userName);
        //HttpSession session = request.getSession();
        session.setAttribute("userId", userName);
        
        AppUser theUser = appUserDAO.findUserAccount(userName);
		session.setAttribute("user", theUser);
    }

}