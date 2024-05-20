package com.Igris.ApplicationGestionAchat.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Igris.ApplicationGestionAchat.Entity.CPT.PrescriptionTechnique;

@Repository
public interface PrescriptionTechniqueRepository extends JpaRepository<PrescriptionTechnique, Integer>{
	public List<PrescriptionTechnique> findByCptReference(String reference);
}
