package com.Igris.ApplicationGestionAchat.Service;

import java.util.List;
//import java.util.Set;

import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat;
import com.Igris.ApplicationGestionAchat.Repository.DemandeAchatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemandeAchatService{
	
	private final DemandeAchatRepository demandeAchatRepository;
	
	public void saveDemandeAchat(DemandeAchat demande) {
		demandeAchatRepository.save(demande);
	}
	
	public List<DemandeAchat> getDemandeAchatByUserMatricule(String matricule){
		return demandeAchatRepository.findAllDemandeAchatByUser(matricule);
	}
}
