package com.Igris.ApplicationGestionAchat.Repository;

import java.util.List;
//import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat;

@Repository
public interface DemandeAchatRepository extends JpaRepository<DemandeAchat, String>{
	
	@Query("""
		    select d from DemandeAchat d
		    where d.user.matricule = :matricule
		""")
		List<DemandeAchat> findAllDemandeAchatByUser(@Param("matricule") String matricule);



}
