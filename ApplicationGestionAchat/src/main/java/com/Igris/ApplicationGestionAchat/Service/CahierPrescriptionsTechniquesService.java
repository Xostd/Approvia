package com.Igris.ApplicationGestionAchat.Service;

import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Repository.CahierPrescriptionsTechniquesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CahierPrescriptionsTechniquesService {

	private final CahierPrescriptionsTechniquesRepository cptRepository;
	
	public Long getCptSequenceNextVal() {
		return cptRepository.getSequenceNextVal();
	}
}
