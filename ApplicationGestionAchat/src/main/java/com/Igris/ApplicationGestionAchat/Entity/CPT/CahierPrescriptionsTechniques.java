package com.Igris.ApplicationGestionAchat.Entity.CPT;

import java.time.LocalDate;
import java.util.List;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat.DemandeAchat;
import com.Igris.ApplicationGestionAchat.Entity.DetailEtat;

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
import lombok.ToString;

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
	
	@Column(name="description")
    private String description;
	
    @Column(name="date_echeance")
    private LocalDate dateEcheance;

    @OneToMany(mappedBy="cpt")
    private List<PrescriptionTechnique> prescriptionsTechniques;
    
    @OneToOne(mappedBy = "cpt")
    @JoinColumn(name="demande_reference")
	@ToString.Exclude
    private DemandeAchat demande;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetailEtat> etats;
	
	public static String generateReference(Long sequence) {
		return "CPT-" + LocalDate.now().getYear() + "-" + String.format("%08d", sequence);
	}
}
