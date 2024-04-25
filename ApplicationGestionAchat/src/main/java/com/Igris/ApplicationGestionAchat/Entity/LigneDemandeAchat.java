package com.Igris.ApplicationGestionAchat.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Entity
@Table(name="LigneDemandeAchat")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LigneDemandeAchat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ref_demande_achat")
	private DemandeAchat demandeAchat;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_reference")
	private Article article;
	
	@Column(name="quantite")
	private float quantite;
}