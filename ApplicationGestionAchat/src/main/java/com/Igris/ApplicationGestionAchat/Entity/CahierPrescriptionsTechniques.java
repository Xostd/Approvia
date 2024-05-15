package com.Igris.ApplicationGestionAchat.Entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cahier_prescriptions_techniques")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CahierPrescriptionsTechniques {

	@Id
	@Column(name="reference")
	private String reference;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "auditeur_matricule")
	private User user;
	
	public static String generateReference(Long sequence) {
		return "CPT-" + LocalDate.now().getYear() + "-" + String.format("%08d", sequence);
	}
}
