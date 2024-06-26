package com.Igris.ApplicationGestionAchat.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat.LigneDemandeAchat;

@Repository
public interface LigneDemandeAchatRepository extends JpaRepository<LigneDemandeAchat, String>{

	
	@Query("""
			  select l from LigneDemandeAchat l
			    join DemandeAchat d on d.reference = l.demandeAchat.reference
			  where d.reference = :reference
			""")
			Set<LigneDemandeAchat> findAllLigneDemandeAchatByDemandeAchat(String reference);

}
