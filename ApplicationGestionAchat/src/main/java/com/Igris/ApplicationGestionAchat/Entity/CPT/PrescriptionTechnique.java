package com.Igris.ApplicationGestionAchat.Entity.CPT;

import com.Igris.ApplicationGestionAchat.Entity.DemandeAchat.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="PrescriptionTechnique")
public class PrescriptionTechnique {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@ToString.Exclude
	@JsonIgnore
	private CahierPrescriptionsTechniques cpt;
	
    @ManyToOne
    @JoinColumn(name="article_reference")
	private Article article;
	
	@Column(name="specification_techniques")
    private String specificationTechnique;//fonctionnel
    
    @Column(name="condition_performance")
    private String conditionPerformance;//non fonctionnel
}
