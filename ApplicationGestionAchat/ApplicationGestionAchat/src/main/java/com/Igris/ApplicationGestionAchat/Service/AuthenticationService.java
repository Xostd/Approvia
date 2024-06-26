package com.Igris.ApplicationGestionAchat.Service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Entity.AuthenticationResponse;
import com.Igris.ApplicationGestionAchat.Entity.Role;
import com.Igris.ApplicationGestionAchat.Entity.Token;
import com.Igris.ApplicationGestionAchat.Entity.User;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authManager;
	private final TokenService tokenService;
	
	private void saveUserToken(User user, String tokenString) {
		Token token = Token.builder()
				.loggedOut(false)
				.token(tokenString)
				.user(user)
				.build();
		tokenService.saveToken(token);
	}
	
	public AuthenticationResponse register(User userRequest) throws Exception{
		User user = User.builder()
				.nom(userRequest.getNom())
				.prenom(userRequest.getPrenom())
				.mdps(passwordEncoder.encode(userRequest.getMdps()))//passing Hashed password
				.service(userRequest.getService())
				.region(userRequest.getRegion())
				.matricule(
						User.generateId(
							userRequest.getRegion()
							, userRequest.getService()
							, userService.getSequenceNextVal())
						)
				.role(Role.DEMANDEUR)
				.build();
		System.out.println(user);
		userService.saveUser(user);
		String token=jwtService.generateToken(user);
		saveUserToken(user, token);
		return new AuthenticationResponse(token);
	}
	
	public AuthenticationResponse authenticate(User userRequest,HttpServletResponse response) throws Exception {
		String matricule = userRequest.getMatricule();
		String mdps= userRequest.getMdps();
		authManager.authenticate(new UsernamePasswordAuthenticationToken(matricule,mdps));
		User user = this.userService.getUserByMatricule(userRequest.getMatricule()).orElseThrow();
		String token = jwtService.generateToken(user);
		saveUserToken(user,token);
		System.out.println("logged in !");
		
		Cookie tokenCookie= new Cookie("Token",token);
		tokenCookie.setHttpOnly(true);//To make the jwt token unmodifiable by the client-side
		tokenCookie.setSecure(true);//to make sure the cookie will be sent over HTTPS only
		response.addCookie(tokenCookie);
		
		Cookie matriculeCookie = new Cookie("Matricule", user.getMatricule());
		matriculeCookie.setSecure(true);
		response.addCookie(matriculeCookie);
		
		Cookie roleCookie=new Cookie("Role",user.getRole().toString());
		roleCookie.setSecure(true);
		response.addCookie(roleCookie);
		
		return new AuthenticationResponse(token);
	}
}
