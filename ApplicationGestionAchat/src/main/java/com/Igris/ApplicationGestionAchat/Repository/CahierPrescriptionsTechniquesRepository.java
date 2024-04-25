package com.Igris.ApplicationGestionAchat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Igris.ApplicationGestionAchat.Entity.CahierPrescriptionsTechniques;

@Repository
public interface CahierPrescriptionsTechniquesRepository extends JpaRepository<CahierPrescriptionsTechniques, String>{
	
	@Query(value = "select PK_CPT.NEXTVAL from dual", nativeQuery = true)
	public Long getSequenceNextVal();
	
	

}
