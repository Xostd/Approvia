package com.Igris.ApplicationGestionAchat.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Igris.ApplicationGestionAchat.Entity.Etat;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/etat")
public class EtatController {
	
	  @GetMapping("demande")
	  public List<String> getEtatDemande() {
		  List<String> etats = List.of(
				  Etat.CREE.name(),
				  Etat.MODIFIE.name(),
				  Etat.APPROUVE_SUPERVISOR.name(),
				  Etat.APPROUVE_ACHETEUR.name(),
				  Etat.RECEPTION_PROVESOIRE.name(),
				  Etat.RECEPTION_FINALE.name(),
				  Etat.ANNULE.name(),
				  Etat.REJETE.name()
				  );
	    return etats;
	  }
	  
	  @GetMapping("cpt")
	  public List<String> getEtatCpt() {
		  List<String> etats = List.of(
				  Etat.CREE.name(),
				  Etat.MODIFIE.name(),
				  Etat.APPROUVE_SUPERVISOR.name(),
				  Etat.ANNULE.name(),
				  Etat.REJETE.name()
				  );
	    return etats;
	  }
	  
	  @GetMapping("ccap")
	  public List<String> getEtatCCAP() {
		  List<String> etats = List.of(
				  Etat.CREE.name(),
				  Etat.MODIFIE.name(),
				  Etat.APPROUVE_SUPERVISOR.name(),
				  Etat.ANNULE.name(),
				  Etat.REJETE.name()
				  );
	    return etats;
	  }
	  
	  @GetMapping("cc")
	  public List<String> getEtatCC() {
		  List<String> etats = List.of(
				  Etat.CREE.name(),
				  Etat.MODIFIE.name(),
				  Etat.APPROUVE_CACC.name(),
				  Etat.REJETE.name(),
				  Etat.ANNULE.name()
				  );
	    return etats;
	  }
	  
	  @GetMapping("Avis")
	  public List<String> getEtatAis() {
		  List<String> etats = List.of(
				  Etat.CREE.name(),
				  Etat.MODIFIE.name(),
				  Etat.APPROUVE_SUPERVISOR.name(),
				  Etat.ANNULE.name(),
				  Etat.REJETE.name()
				  );
	    return etats;
	  }
}
