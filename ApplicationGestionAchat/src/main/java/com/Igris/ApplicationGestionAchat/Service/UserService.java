package com.Igris.ApplicationGestionAchat.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Entity.User.User;
import com.Igris.ApplicationGestionAchat.Repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRep;

	public UserService(UserRepository userRep) {
		super();
		this.userRep = userRep;
	}

	public ArrayList<User> getAllUsers() {
		return (ArrayList<User>) userRep.findAll();
	}
	public ArrayList<User> getUsersByRegion(String region){
		return userRep.findByRegion(region);
	}
	public ArrayList<User> getUsersByUserRegion(String matricule){
		Optional<User> user =userRep.findByMatricule(matricule);
		return userRep.findByRegion(user.get().getRegion().name());
	}
	
	public ArrayList<User> getUsersByService(String service){
		return userRep.findByService(service);
	}
	
	public ArrayList<User> getUsersByNom(String nom){
		return userRep.findByNom(nom);
	}
	
	public ArrayList<User> getUsersByPrenom(String prenom){
		return userRep.findByPrenom(prenom);
	}
	
	public Optional<User> getUserByMatricule(String matricule){
		return userRep.findByMatricule(matricule);
	}
	
//	public Set<Role> getUserRoles(String matricule){
//		return userRep.getUserRoles(matricule);
//	}

	
//    public void setUserRolesByUsername(String matricule, Set<Role> roles) {
//    	this.userRep.setUserRoleByUsername(matricule, roles);
//    }
	
	public void saveUser(User user) {
		userRep.save(user);
	}
	
	public Long getSequenceNextVal() {
		return userRep.getSequenceNextVal();
	}
	
}
