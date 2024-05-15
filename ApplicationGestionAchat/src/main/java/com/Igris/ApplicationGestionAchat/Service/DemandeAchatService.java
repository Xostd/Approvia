package com.Igris.ApplicationGestionAchat.Service;

import java.util.List;
//import java.util.Set;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat;
import com.Igris.ApplicationGestionAchat.Entity.Region;
import com.Igris.ApplicationGestionAchat.Entity.User;
import com.Igris.ApplicationGestionAchat.Repository.DemandeAchatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemandeAchatService{
	
	private final DemandeAchatRepository demandeAchatRepository;
	
	public void saveDemandeAchat(DemandeAchat demande) {
		demandeAchatRepository.save(demande);
	}
	
	public List<DemandeAchat> getDemandeAchatByAuteur(String matricule){
		return demandeAchatRepository.findByAuteur(matricule);
	}
	
//	public List<DemandeAchat> getByUser(User user){
//		return demandeAchatRepository.findByUser(user);
//	}
	
	public Optional<DemandeAchat> getByReference(String Reference) {
		return demandeAchatRepository.findByReference(Reference);
	}
	
	public List<DemandeAchat> getDemandeAchatByUserRegion(Region region){
		return demandeAchatRepository.findAllDemandeAchatByRegion(region);
	}
	
	public List<DemandeAchat> getDemandeAchatByUserRegionAndService(Region region
			,com.Igris.ApplicationGestionAchat.Entity.Service service){
		return demandeAchatRepository
				.findAllDemandeAchatByRegionAndService(region,service);
	}
	
	public void deleteDemande(DemandeAchat demande) {
		demandeAchatRepository.delete(demande);
	}
	
	public Long getDemandeSequenceNextVal() {
		return demandeAchatRepository.getSequenceNextVal();
	}
	
	public void resetDemandeSequence() {
		demandeAchatRepository.resetSequence();
	}
}
