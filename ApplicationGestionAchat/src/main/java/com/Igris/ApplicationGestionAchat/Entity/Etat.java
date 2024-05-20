package com.Igris.ApplicationGestionAchat.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Etat {

	CREE("Créé"),
	MODIFIE("Modifié"),
	
	APPROUVE("Approuvé"),
	APPROUVE_SUPERVISOR("Approuvé Superviseur"),
	APPROUVE_ACHETEUR("Approuvé Acheteur"),
	APPROUVE_CACC("Appouvé CACC"),
	
	RECEPTION_PROVESOIRE("Réception Provesoire"),
	RECEPTION_FINALE("Réception Finale"),
	
	ANNULE("Annulé"),
	REJETE("Rejeté");
	;
	@Getter
	private final String etat;
}
