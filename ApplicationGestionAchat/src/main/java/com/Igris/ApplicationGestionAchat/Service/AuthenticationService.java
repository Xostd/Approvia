package com.Igris.ApplicationGestionAchat.Service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Entity.AuthenticationResponse;
import com.Igris.ApplicationGestionAchat.Entity.Role;
import com.Igris.ApplicationGestionAchat.Entity.Token;
import com.Igris.ApplicationGestionAchat.Entity.User;

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
		saveUserToken(user, token);;
		return AuthenticationResponse.builder().token(token).build();
	}
	
	public AuthenticationResponse authenticate(User userRequest,HttpServletResponse response) throws Exception {
		String matricule = userRequest.getMatricule();
		//String mdps= passwordEncoder.encode(userRequest.getMdps());
		String mdps= userRequest.getMdps();
		System.out.println(mdps);
		authManager.authenticate(new UsernamePasswordAuthenticationToken(matricule,mdps));
		User user = this.userService.getUserByMatricule(userRequest.getMatricule()).orElseThrow();
		String token = jwtService.generateToken(user);
		saveUserToken(user,token);
		System.out.println("logged in !");
		
//		Cookie tokenCookie= new Cookie("Token",token);
//		tokenCookie.setHttpOnly(true);//To make the jwt token unmodifiable by the client-side
//		tokenCookie.setSecure(true);//to make sure the cookie will be sent over HTTPS only
//		response.addCookie(tokenCookie);
//		
//		Cookie matriculeCookie = new Cookie("Matricule", user.getMatricule());
//		matriculeCookie.setSecure(true);
//		response.addCookie(matriculeCookie);
//		
//		Cookie roleCookie=new Cookie("Role",user.getRole().toString());
//		roleCookie.setDomain("localhost");
//		//roleCookie.setSecure(true);// this line was remove since localhost is not secure (HTTPS)
//		response.addCookie(roleCookie);
		AuthenticationResponse authResponse = AuthenticationResponse.builder()
				.token(token)
				.role(user.getRole().name())
				.build();
		System.out.println(authResponse);
		return authResponse;
	}
}
