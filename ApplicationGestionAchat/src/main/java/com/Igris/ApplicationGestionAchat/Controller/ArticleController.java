package com.Igris.ApplicationGestionAchat.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat.Article;
import com.Igris.ApplicationGestionAchat.Service.ArticleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/article")
public class ArticleController {
	
	private final ArticleService articleService;

	  @GetMapping("getAllArticles")
	  public List<Article> getArticles(){
		  List<Article> articles = articleService.getAllArticles();
		  System.out.println(articles);
		  return articles;
	  }
	  
	  @GetMapping("getArticle/{reference}")
	  public Article getArticleById(@PathVariable("reference") Integer reference){
		  return articleService.getArticle(reference).orElseThrow();
	  }
}
