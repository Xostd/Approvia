package com.Igris.ApplicationGestionAchat.Controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Igris.ApplicationGestionAchat.Entity.DetailEtat;
import com.Igris.ApplicationGestionAchat.Entity.Etat;
import com.Igris.ApplicationGestionAchat.Entity.CPT.CahierPrescriptionsTechniques;
import com.Igris.ApplicationGestionAchat.Entity.User.User;
import com.Igris.ApplicationGestionAchat.Service.CahierPrescriptionsTechniquesService;
import com.Igris.ApplicationGestionAchat.Service.DemandeAchatService;
import com.Igris.ApplicationGestionAchat.Service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/cpt")
public class CahierPrescriptionsTechniquesController {
	
	private final DemandeAchatService demandeAchatService;
	private final UserService userService;
	private final CahierPrescriptionsTechniquesService cptService;
	
	@PostMapping("create/{matricule}")
	public ResponseEntity<String> addCpt(
			@RequestBody CahierPrescriptionsTechniques cptRequest,
			@PathVariable("matricule") String matricule){
		if(cptRequest==null)
			return ResponseEntity.ok("Empty RequestBody");
		
		  User user;
		  try {
		        user = userService.getUserByMatricule(matricule).orElseThrow();
		    } catch (NoSuchElementException e) {
		        return ResponseEntity.ok("User with matricule " + matricule + " not found");
		    }
		  cptRequest.setReference(
				  CahierPrescriptionsTechniques.generateReference(
						  cptService.getCptSequenceNextVal()
						  ));
		  cptRequest.setEtats(
				  Collections.singletonList(
						  DetailEtat.builder()
		                            .etat(Etat.CREE)
		                            .date(LocalDate.now())
		                            .user(user)
		                            .build())
				  );
		  cptRequest.getDemande().setCpt(cptRequest);
		  cptService.addCpt(cptRequest);
		  System.out.println("success");
//		System.out.println(">>> matricule :"+matricule);
//		System.out.println(">>> cpt request"+cptRequest);
		return ResponseEntity.ok("added");
	}

}
