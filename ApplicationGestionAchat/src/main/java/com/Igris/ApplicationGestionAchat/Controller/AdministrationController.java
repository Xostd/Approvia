package com.Igris.ApplicationGestionAchat.Controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Igris.ApplicationGestionAchat.Entity.User;
import com.Igris.ApplicationGestionAchat.Service.UserService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/admin")
public class AdministrationController {

	private final UserService userServ;
	
	@GetMapping("/getAllUsers")
	public ArrayList<User> getUsers(){
		return userServ.getAllUsers();
	}

	@GetMapping("/getUsersByRegion")
	public ArrayList<User> getUsersByRegion(@RequestParam String region){
		return userServ.getUsersByRegion(region);
	}
	
	@GetMapping("/getUsersByUserRegion")
	public ArrayList<User> getUsersByUserRegion(@RequestParam String matricule){
		return userServ.getUsersByUserRegion(matricule);
	}
	@GetMapping("/getUsersByService")
	public ArrayList<User> getUsersByService(@RequestParam String service){
		return userServ.getUsersByService(service);
	}
	
	@GetMapping("/getUsersByNom")
	public ArrayList<User> getUsersByNom(@RequestParam String nom){
		return userServ.getUsersByNom(nom);
	}
	
	@GetMapping("/getUsersByPrenom")
	public ArrayList<User> getUsersByPrenom(@RequestParam String prenom){
		return userServ.getUsersByPrenom(prenom);
	}
	
//	@PatchMapping("/setUserRole")
//	public void setUserRole(@RequestParam String role,@RequestParam String matricule){
//		Set<Role> roles = userServ.getUserRoles(matricule);
//		if(!roles.contains(Role.valueOf(role.toUpperCase())))
//			userServ.setUserRolesByUsername(matricule, roles);		
//	}
}
