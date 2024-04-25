package com.Igris.ApplicationGestionAchat.Entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="demande_achat")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemandeAchat {
	@Id
	@Column(name="reference")
	private String reference;
	
	@Column(name="date_creation")
	private LocalDate dateCreation;
	
	@Column(name="etat")
	@Enumerated(value = EnumType.STRING)
	private Etat etat;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_matricule")
	private User user;
	
	@OneToMany(mappedBy = "demandeAchat", cascade = CascadeType.PERSIST)
	private Set<LigneDemandeAchat> lignes;
	
	// -----------------------------Generate new unique reference------------------------------------
	public static String generateReference(Long sequence) {
		return "DA-" + LocalDate.now().getYear() + "-" + String.format("%05d", sequence);
	}

}
