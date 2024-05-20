package com.Igris.ApplicationGestionAchat.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Entity.CPT.PrescriptionTechnique;
import com.Igris.ApplicationGestionAchat.Repository.PrescriptionTechniqueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrescriptionTechniqueService {
	
	private final PrescriptionTechniqueRepository prescriptionTechniqueRepository;
	
	public Optional<PrescriptionTechnique> getPrescriptionTechnique(Integer id){
		return prescriptionTechniqueRepository.findById(id);
	}
	
	public List<PrescriptionTechnique> getByCptReference(String reference){
		return prescriptionTechniqueRepository.findByCptReference(reference);
	}
}
