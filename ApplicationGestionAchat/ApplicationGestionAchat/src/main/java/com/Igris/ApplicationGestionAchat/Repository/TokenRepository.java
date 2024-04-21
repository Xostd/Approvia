package com.Igris.ApplicationGestionAchat.Repository;

import com.Igris.ApplicationGestionAchat.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
		select t from Token t
		  inner join user u on t.user.matricule = u.matricule
		  where u.matricule = :matricule and t.loggedOut = false
	""")
    List<Token> findAllTokensByUser(String matricule);

    Optional<Token> findByToken(String token);
}