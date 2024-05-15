package com.Igris.ApplicationGestionAchat.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationResponse {
	
	private String token;
	private String message;
	private String role;
	private String permission;
}
