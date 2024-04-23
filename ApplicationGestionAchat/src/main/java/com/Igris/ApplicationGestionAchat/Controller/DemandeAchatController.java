package com.Igris.ApplicationGestionAchat.Controller;

import java.util.List;
//import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat;
import com.Igris.ApplicationGestionAchat.Service.DemandeAchatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/demandeAchat")
public class DemandeAchatController {
	
	private final DemandeAchatService demandeAchatService;
	
	  @GetMapping("getDemandeAchatByUser")
	  public List<DemandeAchat> getDemandeAchatByUser(@RequestBody String matricule) {
		 return demandeAchatService.getDemandeAchatByUserMatricule(matricule);
	  }
}
