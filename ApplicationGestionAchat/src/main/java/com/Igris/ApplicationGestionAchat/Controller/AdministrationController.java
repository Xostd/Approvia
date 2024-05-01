package com.Igris.ApplicationGestionAchat.Controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("/getUsersByRegion/{region}")
	public ArrayList<User> getUsersByRegion(@PathVariable("region") String region){
		return userServ.getUsersByRegion(region);
	}
	
	@GetMapping("/getUsersByUserRegion{matricule}")
	public ArrayList<User> getUsersByUserRegion(@PathVariable("matricule") String matricule){
		return userServ.getUsersByUserRegion(matricule);
	}
	@GetMapping("/getUsersByService/{service}")
	public ArrayList<User> getUsersByService(@PathVariable("service") String service){
		return userServ.getUsersByService(service);
	}
	
	@GetMapping("/getUsersByNom/{nom}")
	public ArrayList<User> getUsersByNom(@PathVariable("nom") String nom){
		return userServ.getUsersByNom(nom);
	}
	
	@GetMapping("/getUsersByPrenom/{prenom}")
	public ArrayList<User> getUsersByPrenom(@PathVariable("prenom") String prenom){
		return userServ.getUsersByPrenom(prenom);
	}
	
//	@PatchMapping("/setUserRole")
//	public void setUserRole(@RequestParam String role,@RequestParam String matricule){
//		Set<Role> roles = userServ.getUserRoles(matricule);
//		if(!roles.contains(Role.valueOf(role.toUpperCase())))
//			userServ.setUserRolesByUsername(matricule, roles);		
//	}
}
