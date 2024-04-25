package com.Igris.ApplicationGestionAchat.Controller;

import java.util.List;
//import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat;
import com.Igris.ApplicationGestionAchat.Entity.User;
import com.Igris.ApplicationGestionAchat.Service.DemandeAchatService;
import com.Igris.ApplicationGestionAchat.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/demandeAchat")
public class DemandeAchatController {
	
	private final DemandeAchatService demandeAchatService;
	private final UserService userService;
	
	  @GetMapping("getDemandeAchatByUser")
	  public List<DemandeAchat> getAllDemandeAchatByUser(String matricule) {
		  System.out.println(matricule);
		 return demandeAchatService.getDemandeAchatByUserMatricule(matricule);
	  }
	  
	  @GetMapping("getDemandeAchatByRegion")
	  public List<DemandeAchat> getAllDemandeAchatByRegion(String matricule) {
		  System.out.println(matricule);
		  User user = userService.getUserByMatricule(matricule).orElseThrow();
		 return demandeAchatService.getDemandeAchatByUserRegion(user.getRegion().toString());
	  }
}
