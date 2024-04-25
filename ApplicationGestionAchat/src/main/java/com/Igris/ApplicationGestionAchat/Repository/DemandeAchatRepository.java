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
		    and d.etat in ('CREEE','MODIFIEE')
		""")
		List<DemandeAchat> findAllDemandeAchatByUser(@Param("matricule") String matricule);

	@Query("""
		    select d from DemandeAchat d
		    where d.user.region = :region 
		    and d.etat not in ('CREEE','MODIFIEE' ,'TERMINEE')
		""")
		List<DemandeAchat> findAllDemandeAchatByRegion(@Param("region") String region);
	
	@Query(value = "select PK_DA.NEXTVAL from dual", nativeQuery = true)
		Long getSequenceNextVal();
	
	@Query(value ="ALTER SEQUENCE PK_DA RESTART WITH 1", nativeQuery = true)
	void resetSequence();


}
