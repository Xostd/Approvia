package com.Igris.ApplicationGestionAchat.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Entity.CPT.CahierPrescriptionsTechniques;
import com.Igris.ApplicationGestionAchat.Repository.CahierPrescriptionsTechniquesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CahierPrescriptionsTechniquesService {

	private final CahierPrescriptionsTechniquesRepository cptRepository;
	
	public void addCpt(CahierPrescriptionsTechniques cpt) {
		cptRepository.save(cpt);
	}
	
	public Optional<CahierPrescriptionsTechniques> getCpt(String reference){
		return cptRepository.findById(reference);
	}
	
	public List<CahierPrescriptionsTechniques> getAllCpt(){
		return cptRepository.findAll();
	}
	
	
	public Long getCptSequenceNextVal() {
		return cptRepository.getSequenceNextVal();
	}
}
