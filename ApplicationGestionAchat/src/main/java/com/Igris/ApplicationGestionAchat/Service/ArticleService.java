package com.Igris.ApplicationGestionAchat.Service;

import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Entity.Article;
import com.Igris.ApplicationGestionAchat.Repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;
	
	public void saveArtice(Article article) {
		articleRepository.save(article);
	}
}
