package com.Igris.ApplicationGestionAchat.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
//import java.util.Set;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Igris.ApplicationGestionAchat.Entity.Etat;
import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat.DemandeAchat;
import com.Igris.ApplicationGestionAchat.Entity.DetailEtat;
import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat.LigneDemandeAchat;
import com.Igris.ApplicationGestionAchat.Entity.User.Permission;
import com.Igris.ApplicationGestionAchat.Entity.User.Role;
import com.Igris.ApplicationGestionAchat.Entity.User.User;
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
	
	  @GetMapping("getDemandeAchatByAuteur/{matricule}")
	  public List<DemandeAchat> getAllDemandeAchatByAuteur(@PathVariable("matricule")String matricule) {
		  System.out.println(matricule);
		  User user = userService.getUserByMatricule(matricule).orElseThrow();

//		 return demandeAchatService.getDemandeAchatByUserMatricule(matricule);
		  List<DemandeAchat> list = demandeAchatService.getDemandeAchatByAuteur(user.getMatricule());
		  System.out.println(list);
		  return list;
	  }
	  
	  @GetMapping("getDemandeAchatByRegion/{matricule}")
	  public List<DemandeAchat> getAllDemandeAchatByRegion(@PathVariable("matricule") String matricule) {
		  System.out.println(matricule);
		  User user=null;
		  try {
			  user = userService.getUserByMatricule(matricule).orElseThrow();
		  }catch(Exception e) {
			  e.printStackTrace(); 
			  return null;
		  }
		  List<DemandeAchat> demandes =demandeAchatService.getDemandeAchatByUserRegion(user.getRegion());
		 return demandes;
	  }
	  
	  @GetMapping("getDemandeAchatByRegionAndService/{matricule}")
	  public List<DemandeAchat> getAllDemandeAchatByRegionAndService(@PathVariable("matricule") String matricule) {
		  System.out.println(matricule);
		  User user=null;
		  try {
			  user = userService.getUserByMatricule(matricule).orElseThrow();
		  }catch(Exception e) {
			  e.printStackTrace();
			  return null;
		  }
		  List<DemandeAchat> demandes =demandeAchatService
				  .getDemandeAchatByUserRegionAndService(user.getRegion(),user.getService());
		  	
		 return demandes;
	  }
	  
	  @GetMapping("getDemandeAchatByReference/{reference}")
	  public DemandeAchat getDemandeAchatByReference(@PathVariable("reference") String reference) {
		  DemandeAchat demande=demandeAchatService.getByReference(reference).orElseThrow();
		  Set<LigneDemandeAchat> lignes = ligneDemandeAchatService.getLigneDemandeAchatByDemande(reference);
		  demande.setLignes(lignes);
		  System.out.println(">>>\n"+lignes);
		  System.out.println(">>>>>\n"+demande);
		 return demande;
	  }
	  @PostMapping("create/{matricule}")
	  public ResponseEntity<String> createDemande(@RequestBody DemandeAchat demandeRequest
			  ,@PathVariable("matricule") String matricule) {
		    if (demandeRequest == null) {
		        return ResponseEntity.ok("Empty request body");
		    }
		  User user;
		  try {
		        user = userService.getUserByMatricule(matricule).orElseThrow();
		    } catch (NoSuchElementException e) {
		        return ResponseEntity.ok("User with matricule " + matricule + " not found");
		    }
		    double somme = demandeRequest.getLignes().stream().mapToDouble(ligne -> ligne.getPrix()).sum();
		    DemandeAchat demande = DemandeAchat.builder()
				  .reference(DemandeAchat.generateReference(demandeAchatService.getDemandeSequenceNextVal()))
				  .dateCreation(LocalDate.now())
				  .description(demandeRequest.getDescription())
//				  .lignes(demandeRequest.getLignes())
				  .etats(Collections.singletonList(
						  DetailEtat.builder()
		                            .etat(Etat.CREE)
		                            .date(LocalDate.now())
		                            .user(user)
		                            .build()))
				  .somme(somme)
				  .build();		  
		  demandeRequest.getLignes().forEach((ligne)->{
		  ligne.setDemandeAchat(demande);
		  System.out.println(ligne);
	  });
//		  demandeAchatService.saveDemandeAchat(demande);
		    ligneDemandeAchatService.saveAllLigneDemandeAchat(demandeRequest.getLignes());

		  return ResponseEntity.ok("demande created");
	  }
	//------------------------------------------------  
	  @PostMapping("update/{matricule}")
	  public String updateDemande(@RequestBody DemandeAchat demandeRequest
			  ,@PathVariable("matricule") String matricule) {
		  float somme=0;

		  if(demandeRequest==null) {
			  return "empty Request Body";
		  }
		  //---------------------
		  User user;
		  try {
			  user=userService.getUserByMatricule(matricule).orElseThrow();
		  }
		  catch(Exception e) {
			  e.printStackTrace();
			  return "user not found";
		  }
		  for(LigneDemandeAchat ligne : demandeRequest.getLignes()) {
			  ligne.setPrix(ligne.getArticle().getPrixUnitaire()*ligne.getQuantite());
			  somme+=ligne.getPrix();
		  }
			 DemandeAchat demande = demandeAchatService.getByReference(demandeRequest.getReference()).orElseThrow();
//		  System.out.println(">>>> matricule\n"+ matricule +"\n>>>>");
//		  System.out.println(">>>>reference\n"+ reference +"\n>>>>");
//		  System.out.println(">>>>demande\n"+ demande.getReference() +"\n>>>>");
//		  System.out.println(">>>>demande\n"+ demande +"\n>>>>");

			  demande.getEtats().add(
					  DetailEtat.builder()
					  .etat(Etat.MODIFIE)
					  .date(LocalDate.now())
					  .user(user)
//					  .demandeAchat(demande)
					  .build()
					  );
			  demande.setDescription(demandeRequest.getDescription());
		  demande.setSomme(somme);
//		  demande.setDateCreation(LocalDate.now());		  
//		  DemandeAchat demandeNew =DemandeAchat.builder()
//				  .reference(reference==null?
//						  DemandeAchat.generateReference(demandeAchatService.getDemandeSequenceNextVal())
//						  :reference)
//				  .dateCreation(LocalDate.now())
//				  //.lignes(lignes)
//				  .etat(reference==null?Etat.CREEE:Etat.MODIFIEE)
//				  .user(reference==null?user:null)
//				  .modifierPar(reference==null?null:user)
//				  .somme(somme)
//				  .build();
		  demandeRequest.getLignes().forEach((ligne)->{
			  ligne.setDemandeAchat(demande);
			  System.out.println(ligne);
		  });
		  demandeAchatService.saveDemandeAchat(demande);
		  ligneDemandeAchatService.saveAllLigneDemandeAchat(demandeRequest.getLignes());
		  return "demande updated";
		  }
		//------------------------------------------------  
	  
	  @PutMapping("reject/{matricule}/{reference}")
	  public ResponseEntity<String> reject(@PathVariable("matricule") String matricule,@PathVariable String reference) {
		  if(reference==""||reference==null) {
			  return ResponseEntity.ok("null Demande");
		  }
		  if(matricule==""||matricule==null) {
			  return ResponseEntity.ok("invalid matricule");
		  }
		  User user;
		  try {
			user = userService.getUserByMatricule(matricule).orElseThrow();
		  }catch(Exception e) {
			  return ResponseEntity.ok("User Not Found");
		  }
		  DemandeAchat demande;
		  try {
			  demande = demandeAchatService.getByReference(reference).orElseThrow();
		  }catch(Exception e) {
			  return ResponseEntity.ok("Demande not found");
		  }
			 User auteur = demande.getEtats().get(0).getUser();
			 //----------
			 DetailEtat etat= demande.getEtats().get(demande.getEtats().size()-1);
			System.out.println(etat);
			boolean isValid=!etat.getEtat().equals(Etat.CREE)&&!etat.getEtat().equals(Etat.MODIFIE);
				 
			 System.out.println(demande.getReference());
			 System.out.println(auteur.getMatricule());
			 System.out.println(user.getMatricule());
			 //--------------------
			 if((auteur.getMatricule()==user.getMatricule()
					 ||(auteur.getRegion().equals(user.getRegion())
							 && auteur.getService().equals(user.getService())
							 &&user.getPermission().equals(Permission.SUPERVISOR))
					 )
					 &&!isValid) {
				 demande.getEtats().add(
						 DetailEtat.builder()
						 .date(LocalDate.now())
//						 .demandeAchat(demande)
						 .user(user)
						 .etat(Etat.ANNULE)
						 .build()
						 );
				  System.out.println("demandeur mat >>"+auteur.getMatricule());
				  System.out.println("deleted");
				  ligneDemandeAchatService.saveAllLigneDemandeAchat(demande.getLignes());
				  return ResponseEntity.ok("annulee");

			 }
		  if( auteur.getRegion().equals(user.getRegion())
				  &&user.getRole().equals(Role.ACHETEUR)) {
				 demande.getEtats().add(
						 DetailEtat.builder()
						 .date(LocalDate.now())
//						 .demandeAchat(demande)
						 .user(user)
						 .etat(Etat.REJETE)
						 .build()
						 );
				  ligneDemandeAchatService.saveAllLigneDemandeAchat(demande.getLignes());
				  return ResponseEntity.ok("rejetee");

		  }
		  return ResponseEntity.ok("no action done in reject demande");
//		  System.out.println(" matricule : >>"+matricule);
//		  System.out.println(" reference : >>"+reference);
	  }
	  
	  @PutMapping("validate/{reference}/{matricule}")
	  public ResponseEntity<String> validate(@PathVariable("matricule") String matricule
			  ,@PathVariable("reference") String reference) {
		  
		  if(matricule==null||matricule=="") {
			  return ResponseEntity.ok("Invalid matricule");
		  }
		  if(reference==null||reference=="") {
			  return ResponseEntity.ok("Invalid reference");
		  }
		  User user;
		  try {
			  		user = userService.getUserByMatricule(matricule).orElseThrow();

		  }catch(Exception e) {
			  return ResponseEntity.ok("User not found");
		  }
		DemandeAchat demande ;
		try{
			demande= demandeAchatService.getByReference(reference).orElseThrow();
		}catch(Exception e) {
			  return ResponseEntity.ok("Demande not found");
		}
		//if not validated by a supervisor it will be supervisor validated
		boolean isSuperValid = false;
		for(DetailEtat etat : demande.getEtats()) {
			if(etat.getEtat().equals(Etat.APPROUVE_SUPERVISOR)) {
				isSuperValid=true;
				break;
			}
		}
		  if(user.getPermission().equals(Permission.SUPERVISOR)
				  &&!isSuperValid) {

					  demande.getEtats().add(
							  DetailEtat.builder()
							  .etat(Etat.APPROUVE_SUPERVISOR)
							  .date(LocalDate.now())
							  .user(user)
//							  .demandeAchat(demande)
							  .build()
							  );
					  demandeAchatService.saveDemandeAchat(demande);
					  return ResponseEntity.ok("validated");
			  }
		  if(user.getRole().equals(Role.ACHETEUR)&&isSuperValid
				  ) {
			  		  demande.getEtats().add(
				  DetailEtat.builder()
				  .etat(Etat.APPROUVE_ACHETEUR)
				  .date(LocalDate.now())
				  .user(user)
//				  .demandeAchat(demande)
				  .build()
				  );
		  demandeAchatService.saveDemandeAchat(demande);

		  return ResponseEntity.ok("validated");
		  }
		  return ResponseEntity.ok("No action performed");
	  }
}
