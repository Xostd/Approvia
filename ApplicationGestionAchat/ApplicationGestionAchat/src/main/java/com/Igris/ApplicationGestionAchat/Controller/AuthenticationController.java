package com.Igris.ApplicationGestionAchat.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Igris.ApplicationGestionAchat.Entity.AuthenticationResponse;
import com.Igris.ApplicationGestionAchat.Entity.User;
import com.Igris.ApplicationGestionAchat.Service.AuthenticationService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:5173")
@RequestMapping("/api/auth")
public class AuthenticationController {

	private final AuthenticationService authService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody User userRequest){
		//System.out.println(userRequest);

		try {
			return ResponseEntity.ok(authService.register(userRequest));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(new AuthenticationResponse("Error Occured at register"));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(
			@RequestBody User userRequest, HttpServletResponse response){
		System.out.println(userRequest);

		try {
			return ResponseEntity.ok(authService.authenticate(userRequest,response));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(new AuthenticationResponse("Error Occured at authenticate"));

	}
}
