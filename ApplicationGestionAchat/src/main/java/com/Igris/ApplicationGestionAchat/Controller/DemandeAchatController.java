package com.Igris.ApplicationGestionAchat.Controller;

import java.time.LocalDate;
import java.util.List;
//import java.util.Set;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat;
import com.Igris.ApplicationGestionAchat.Entity.Etat;
import com.Igris.ApplicationGestionAchat.Entity.LigneDemandeAchat;
import com.Igris.ApplicationGestionAchat.Entity.User;
import com.Igris.ApplicationGestionAchat.Service.DemandeAchatService;
import com.Igris.ApplicationGestionAchat.Service.LigneDemandeAchatService;
import com.Igris.ApplicationGestionAchat.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/demandeAchat")
public class DemandeAchatController {
	
	private final DemandeAchatService demandeAchatService;
	private final UserService userService;
	private final LigneDemandeAchatService ligneDemandeAchatService;
	
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
	  
	  @PostMapping("create/{matricule}")
	  public String createDemande(@RequestBody Set<LigneDemandeAchat> lignes
			  ,@PathVariable("matricule") String matricule) {
		  int somme=0;
		  User user = null;
		  try {
			  user=userService.getUserByMatricule(matricule).orElseThrow();
		  }
		  catch(Exception e) {
			  e.printStackTrace();
			  return "user not found";
		  }
		  for(LigneDemandeAchat ligne : lignes) {
			  ligne.setPrix(ligne.getArticle().getPrixUnitaire()*ligne.getQuantite());
			  somme+=ligne.getPrix();
		  }
		  DemandeAchat demande = DemandeAchat.builder()
				  .reference(DemandeAchat.generateReference(demandeAchatService.getDemandeSequenceNextVal()))
				  .dateCreation(LocalDate.now())
				  //.lignes(lignes)
				  .etat(Etat.CREEE)
				  .user(user)
				  .somme(somme)
				  .build();
		  lignes.forEach((ligne)->{
			  ligne.setDemandeAchat(demande);
			  System.out.println(ligne);
		  });
		  demandeAchatService.saveDemandeAchat(demande);
		  ligneDemandeAchatService.saveAllLigneDemandeAchat(lignes);
		  return "demande created";
	  }
}
