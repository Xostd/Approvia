package com.Igris.ApplicationGestionAchat.Service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Entity.LigneDemandeAchat;
import com.Igris.ApplicationGestionAchat.Repository.LigneDemandeAchatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LigneDemandeAchatService{
	
	private final LigneDemandeAchatRepository ligneDemandeAchatRepository;
	
	public void saveLigneDemandeAchat(LigneDemandeAchat ligne) {
		ligneDemandeAchatRepository.save(ligne);
	}
	public void saveAllLigneDemandeAchat(Set<LigneDemandeAchat> lignes) {
		ligneDemandeAchatRepository.saveAll(lignes);
	}
	
	public Set<LigneDemandeAchat> getLigneDemandeAchatByDemande(String reference){
		return ligneDemandeAchatRepository.findAllLigneDemandeAchatByDemandeAchat(reference);
	}
	
}
