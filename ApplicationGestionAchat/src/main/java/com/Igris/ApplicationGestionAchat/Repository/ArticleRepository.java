package com.Igris.ApplicationGestionAchat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Igris.ApplicationGestionAchat.Entity.Article;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{
	

	
}
