package com.Igris.ApplicationGestionAchat.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	private String reference;
	
	public static String generateReference(Long sequence) {
		return "CPT-" + LocalDate.now().getYear() + "-" + String.format("%05d", sequence);
	}
}
