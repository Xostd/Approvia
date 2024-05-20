package com.Igris.ApplicationGestionAchat.Entity.DemandeAchat;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.Igris.ApplicationGestionAchat.Entity.DetailEtat;
import com.Igris.ApplicationGestionAchat.Entity.CPT.CahierPrescriptionsTechniques;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	
	@Column(name="description")
	private String description;
	
	@Column(name="date_creation")
	private LocalDate dateCreation;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name="etat")
	private List<DetailEtat> etats ;
	
	@Column(name="somme")
	private double somme;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reference_cpt", referencedColumnName = "reference")
	private CahierPrescriptionsTechniques cpt;
	
    @OneToMany(mappedBy = "demandeAchat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<LigneDemandeAchat> lignes;
	
	// -----------------------------Generate new unique reference------------------------------------
	public static String generateReference(Long sequence) {
		return "DA-" + LocalDate.now().getYear() + "-" + String.format("%08d", sequence);
	}

}
