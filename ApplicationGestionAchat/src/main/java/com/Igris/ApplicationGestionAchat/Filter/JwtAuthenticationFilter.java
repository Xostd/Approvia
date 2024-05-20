package com.Igris.ApplicationGestionAchat.Filter;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Igris.ApplicationGestionAchat.Entity.User.User;
import com.Igris.ApplicationGestionAchat.Service.JwtService;
import com.Igris.ApplicationGestionAchat.Service.UserDetailsServiceImp;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	// this class intercepts the http requests before reaching the controllers
	
	private final JwtService jwtService;
	private final UserDetailsServiceImp userDetailsService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request
			, @NonNull HttpServletResponse response
			, @NonNull FilterChain filterChain)
			throws ServletException, IOException {
		String token=null;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			System.out.println(">>>> Cookies\n"+cookies);
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("Token")) {
					token=cookie.getValue();
					break;
				}
			}
		}
			//final String authHeader = request.getHeader("Authorization");
			//if (authHeader == null || !authHeader.startsWith("Bearer ")) {
		if(token==null) {
			//this is for handeling request without token in cookies
			//like the registration for exemple
			filterChain.doFilter(request, response);
			return;
		}
			//final String token = authHeader.substring(7);
		//this part of code is reached when the token is present 
		//the claim matricule will be extracted
		final String matricule = jwtService.extractMatricule(token);
		
		if (matricule != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(matricule);

			if (userDetails != null && jwtService.isValid(token, (User) userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
