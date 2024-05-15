package com.Igris.ApplicationGestionAchat.Controller;

import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Igris.ApplicationGestionAchat.Entity.LigneDemandeAchat;
import com.Igris.ApplicationGestionAchat.Service.LigneDemandeAchatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/ligneDemandeAchat")
public class LigneDemandeController {
	
	private final LigneDemandeAchatService ligneDemandeAchatService;
	
		@GetMapping("getLigneByDemandeReference/{reference}")
		public Set<LigneDemandeAchat> getLignes(@PathVariable("reference") String reference) {
			Set<LigneDemandeAchat> lignes = ligneDemandeAchatService.getLigneDemandeAchatByDemande(reference);
			System.out.println(lignes);
			return lignes;
	  }

}
