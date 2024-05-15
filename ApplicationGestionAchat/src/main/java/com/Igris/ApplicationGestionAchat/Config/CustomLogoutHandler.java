package com.Igris.ApplicationGestionAchat.Config;

import com.Igris.ApplicationGestionAchat.Entity.Token;
import com.Igris.ApplicationGestionAchat.Service.TokenService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenService tokenService;

 
    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {

    	String authHeader=request.getHeader("Authorization");
    	if(authHeader==null||!authHeader.startsWith("Bearer ")) {
    		return;
    	}
    	String token = authHeader.substring(7);
    	//ivalidate the tokens from the db
    	Token storedToken=tokenService.getByToken(token).orElse(null);
    	if(storedToken!=null) {
    		storedToken.setLoggedOut(true);
    		tokenService.saveToken(storedToken);
    	}
    }

}
