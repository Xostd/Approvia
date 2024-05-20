package com.Igris.ApplicationGestionAchat.Service;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat.Article;
import com.Igris.ApplicationGestionAchat.Repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;
	
	public void saveArtice(Article article) {
		articleRepository.save(article);
	}
	
	public List<Article> getAllArticles(){
		return articleRepository.findAll();
	}
	public Optional<Article> getArticle(Integer reference){
		return articleRepository.findByReference(reference);
	}
}
