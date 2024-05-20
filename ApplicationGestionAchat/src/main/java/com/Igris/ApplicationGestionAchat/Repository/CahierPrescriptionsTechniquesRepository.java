package com.Igris.ApplicationGestionAchat.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Igris.ApplicationGestionAchat.Entity.CPT.CahierPrescriptionsTechniques;

@Repository
public interface CahierPrescriptionsTechniquesRepository extends JpaRepository<CahierPrescriptionsTechniques, String>{
	
	@Query(value = "select PK_CPT.NEXTVAL from dual", nativeQuery = true)
	public Long getSequenceNextVal();
	
	public Optional<CahierPrescriptionsTechniques> findByDemandeReference(String reference);
	

}
