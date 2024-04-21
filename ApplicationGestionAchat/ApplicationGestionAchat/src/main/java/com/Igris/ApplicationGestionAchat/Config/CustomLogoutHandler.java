package com.Igris.ApplicationGestionAchat.Config;

import com.Igris.ApplicationGestionAchat.Entity.Token;
import com.Igris.ApplicationGestionAchat.Repository.TokenRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    public CustomLogoutHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
 
    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        // Extract the token from the HTTP-only cookie named 'jwt'
        String token = extractTokenFromCookie(request, "jwt");

        // Perform cleanup based on the extracted token
        if (token != null) {
            Token storedToken = tokenRepository.findByToken(token).orElse(null);
            if (storedToken != null) {
                storedToken.setLoggedOut(true);
                tokenRepository.save(storedToken);
            }
        }

        // Delete HTTP-only cookies 'jwt' and 'role'
        deleteCookie(response, "jwt");
        deleteCookie(response, "role");
    }

    private String extractTokenFromCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void deleteCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setPath("/"); // Cookie path should match the path used during cookie creation
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Optionally set cookie as secure if using HTTPS
        response.addCookie(cookie);
    }
}
