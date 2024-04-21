package com.Igris.ApplicationGestionAchat.Repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Igris.ApplicationGestionAchat.Entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,String>{
	
	Optional<User> findByMatricule(String matricule);
	
	ArrayList<User> findByRegion(String Region);
	
	ArrayList<User> findByService(String Service);
	
	ArrayList<User> findByNom(String nom);
	
	ArrayList<User> findByPrenom(String prenom);
	
//    @Query(value = "select role from Users u where u.matricule = :matricule")
//	Set<Role> getUserRoles(String matricule);
	
//	@Modifying
//    @Transactional
//    @Query(value = "update Users u SET u.roles = :roles where u.matricule = :matricule")
//    void setUserRolesByUsername(String matricule, Set<Role> roles);
	
	@Query(value = "select PK_Users.NEXTVAL from dual", nativeQuery = true)
	public Long getSequenceNextVal();
	
}
