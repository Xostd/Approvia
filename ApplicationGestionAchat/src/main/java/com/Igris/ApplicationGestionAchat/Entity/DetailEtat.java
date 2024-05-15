package com.Igris.ApplicationGestionAchat.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="detail_Etat")
public class DetailEtat {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(value = EnumType.STRING)
	private Etat etat;
	
	@Column(name="date_modification")
	private LocalDate date;
	
	@ManyToOne(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name="user_matricule")
	private User user;
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name="ref_demande_achat")
	private DemandeAchat demandeAchat;

	@Override
	public String toString() {
		return "DetailEtat [id=" + id + ", etat=" + etat + ", date=" + date + ", user=" + user + ", demandeAchat="
				+ demandeAchat.getReference() + "]";
	}
	

}
