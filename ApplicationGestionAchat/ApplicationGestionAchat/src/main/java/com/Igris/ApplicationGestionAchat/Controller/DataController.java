package com.Igris.ApplicationGestionAchat.Controller;

import java.util.ArrayList;
import java.util.EnumSet;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Igris.ApplicationGestionAchat.Entity.Region;
import com.Igris.ApplicationGestionAchat.Entity.Service;

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
}
