package com.Igris.ApplicationGestionAchat.Service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService{

	private final UserService userServ;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userServ.getUserByMatricule(username)
				.orElseThrow(()->new UsernameNotFoundException("User not found"));
	}
	
}
