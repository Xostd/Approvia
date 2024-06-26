package com.Igris.ApplicationGestionAchat.Controller;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Igris.ApplicationGestionAchat.Entity.User.Poste;
import com.Igris.ApplicationGestionAchat.Entity.User.Region;
import com.Igris.ApplicationGestionAchat.Entity.User.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/data")
public class DataController {
	
	
	  @GetMapping("services")
	  public ArrayList<String> getServices() {
		  EnumSet<Service> services = EnumSet.allOf(Service.class);
		  ArrayList<String> servicesArr = new ArrayList<String>();
		  services.forEach(service ->servicesArr.add(service.name()));
	    return servicesArr;
	  }

	  @GetMapping("regions")
	  public ArrayList<String> getRegions() {
		  EnumSet<Region> regions = EnumSet.allOf(Region.class);
		  ArrayList<String> regionsArr = new ArrayList<String>();
		  regions.forEach(region ->regionsArr.add(region.name()));
	    return regionsArr;
	  }
	  
	  @GetMapping("postes")
	  public ArrayList<String> getPostes() {
		  EnumSet<Poste> postes = EnumSet.allOf(Poste.class);
		  ArrayList<String> postesArr = new ArrayList<String>();
		  postes.forEach(poste ->postesArr.add(poste.name()));
	    return postesArr;
	  }
	  
}
