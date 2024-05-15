package com.Igris.ApplicationGestionAchat.Repository;

import java.util.List;
//import java.util.Set;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat;
import com.Igris.ApplicationGestionAchat.Entity.Region;
import com.Igris.ApplicationGestionAchat.Entity.Service;
import com.Igris.ApplicationGestionAchat.Entity.User;

@Repository
public interface DemandeAchatRepository extends JpaRepository<DemandeAchat, String>{
	
    @Query("""
    		SELECT da FROM DemandeAchat da 
    		JOIN da.etats de 
    		WHERE de.user.matricule = :matricule AND de.etat = 'CREEE'
    		""")
    List<DemandeAchat> findByAuteur(String matricule);
	
//	List<DemandeAchat> findByEtatUser( User user);
	
	Optional<DemandeAchat> findByReference(String reference);


	@Query("""
		    SELECT da FROM DemandeAchat da 
    		JOIN da.etats de 
    		WHERE de.user.region = :region 
    		AND de.etat != 'REJETEE'
		    
		""")
		List<DemandeAchat> findAllDemandeAchatByRegion(@Param("region") Region region);
	
	@Query("""
		    SELECT da FROM DemandeAchat da 
    		JOIN da.etats de 
    		WHERE de.user.region = :region 
    		AND de.user.service = :service
		    
		""")
		List<DemandeAchat> findAllDemandeAchatByRegionAndService(@Param("region") Region region
				,@Param("service") Service service);
	
//	@Query("""
//		    delete from DEMANDE_ACHAT where reference= :reference
//		""")
//		List<DemandeAchat> deleteDemande(@Param("reference") String reference);
	
	@Query(value = "select PK_DA.NEXTVAL from dual", nativeQuery = true)
		Long getSequenceNextVal();
	
	@Query(value ="ALTER SEQUENCE PK_DA RESTART WITH 1", nativeQuery = true)
	void resetSequence();


}
