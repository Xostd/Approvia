package com.Igris.ApplicationGestionAchat.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat.Article;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{
	
	Optional<Article> findByReference(Integer reference);
	
}
