package com.Igris.ApplicationGestionAchat.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@Column(name="matricule")
	private String matricule;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="mdps")
	private String mdps;

	@Enumerated(value = EnumType.STRING)
	private Region region;

	@Enumerated(value = EnumType.STRING)
	private Service service;

	@Enumerated(value = EnumType.STRING)
	private Role role;
	
	@Enumerated(value = EnumType.STRING)
	private Poste poste;
	
	@Enumerated(value = EnumType.STRING)
	private Permission permission;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)//
    private List<Token> tokens;

	// -----------------------------Custom Constructor------------------------------------
	public User(String prenom, String nom, String password, Service service, Region region, Role role, Long sequence) {
		this.matricule = generateId(region, service, sequence);
		System.out.println(this.matricule);
		this.nom = nom;
		this.prenom = prenom;
		this.mdps = password;
		this.service = service;
		this.region = region;
		this.role = role;
	}

	// -----------------------------Generate new unique user ID------------------------------------
	public static String generateId(Region region, Service service, Long sequence) {
		String regCode = "";
		String servCode = "";
		try {
			regCode = Region.getCode(region);
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		}
		try {
			servCode = Service.getCode(service);
		} catch (IllegalStateException e2) {
			e2.printStackTrace();
		}
		return regCode + "-" + servCode + "-" + String.format("%08d", sequence);
	}

	// ---------------------------------UserDetails
	// methods-----------------------------------------------------------
	// get user role
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role.name()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return mdps;
	}

	@Override
	public String getUsername() {
		return matricule;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
