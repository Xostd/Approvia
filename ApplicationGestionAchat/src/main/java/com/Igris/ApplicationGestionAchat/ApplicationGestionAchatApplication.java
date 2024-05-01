package com.Igris.ApplicationGestionAchat;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Igris.ApplicationGestionAchat.Entity.Article;
import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat;
import com.Igris.ApplicationGestionAchat.Entity.Etat;
import com.Igris.ApplicationGestionAchat.Entity.LigneDemandeAchat;
import com.Igris.ApplicationGestionAchat.Entity.Region;
import com.Igris.ApplicationGestionAchat.Entity.Role;
import com.Igris.ApplicationGestionAchat.Entity.Service;
import com.Igris.ApplicationGestionAchat.Entity.Token;
import com.Igris.ApplicationGestionAchat.Entity.User;
import com.Igris.ApplicationGestionAchat.Service.ArticleService;
import com.Igris.ApplicationGestionAchat.Service.DemandeAchatService;
import com.Igris.ApplicationGestionAchat.Service.LigneDemandeAchatService;
import com.Igris.ApplicationGestionAchat.Service.TokenService;
import com.Igris.ApplicationGestionAchat.Service.UserService;

@SpringBootApplication
@ComponentScan(basePackages = {"com.Igris.ApplicationGestionAchat"})
public class ApplicationGestionAchatApplication {
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ApplicationGestionAchatApplication.class, args);
		UserService userServ = ctx.getBean(UserService.class);
		PasswordEncoder passwordEncoder = ctx.getBean(PasswordEncoder.class);
//		User user = new User("Ben Foulen", "Foulen", passwordEncoder.encode("password"), Service.Informatique,
//				Region.Sfax, Role.ACHETEUR, userServ.getSequenceNextVal());
		User user2 = new User("Salah", "Mohamed", passwordEncoder.encode("aaaaaaaa"), Service.Finance, Region.Sfax,
				Role.DEMANDEUR, userServ.getSequenceNextVal());
//		user.setRole(Role.ADMIN);
		//userServ.saveUser(user);
		userServ.saveUser(user2);
		System.out.println(user2);
		TokenService tokenService = ctx.getBean(TokenService.class);
		Token token = Token.builder().user(user2).loggedOut(false).token("testToken").build();
		System.out.println(token);
		tokenService.saveToken(token);
		ArticleService articleService = ctx.getBean(ArticleService.class);
		DemandeAchatService demandeService = ctx.getBean(DemandeAchatService.class);
		LigneDemandeAchatService ligneService = ctx.getBean(LigneDemandeAchatService.class);
		Article article = Article.builder()
				.libelle("art-1")
				.prixUnitaire(20)
				.unite("Kg").build();
		Article article2 = Article.builder()
				.libelle("art-2")
				.prixUnitaire(10)
				.unite("L").build();
		Article article3 = Article.builder()
				.libelle("art-3")
				.prixUnitaire(25)
				.unite("M").build();
		Article article4 = Article.builder()
				.libelle("art-4")
				.prixUnitaire(25)
				.unite("M").build();		
		Article article5 = Article.builder()
				.libelle("art-5")
				.prixUnitaire(25)
				.unite("Ton").build();
		Article article6 = Article.builder()
				.libelle("art-6")
				.prixUnitaire(25)
				.unite("g").build();
		DemandeAchat demande = DemandeAchat.builder()
				.reference(DemandeAchat.generateReference(demandeService.getDemandeSequenceNextVal()))
				.dateCreation(LocalDate.now())
				.user(user2)
				.etat(Etat.CREEE)
				.build();
		LigneDemandeAchat ligne = LigneDemandeAchat.builder()
				.article(article)
				.demandeAchat(demande)
				.quantite(10)
				.build();
		//demande.setLignes(Set.of(ligne));

		articleService.saveArtice(article);
		articleService.saveArtice(article2);
		articleService.saveArtice(article3);
		articleService.saveArtice(article4);
		articleService.saveArtice(article5);
		articleService.saveArtice(article6);

		demandeService.saveDemandeAchat(demande);
		ligneService.saveLigneDemandeAchat(ligne);

	}
}
