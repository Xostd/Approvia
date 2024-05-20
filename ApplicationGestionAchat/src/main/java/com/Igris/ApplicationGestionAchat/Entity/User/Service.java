package com.Igris.ApplicationGestionAchat.Entity.User;

public enum Service {
	Finance,
	Ressources_humaines,
	Commerciale,
	Marketing,
	Production,
	Informatique,
	Achat;
	
	public static String getCode(Service service) throws IllegalStateException{
		String code;
		switch(service) {
		case Finance :
			code = "FIN";
			break;
		case Ressources_humaines :
			code ="RH";
			break;
		case Commerciale :
			code = "COM";
			break;
		case Marketing :
			code = "MKTG";
			break;
		case Production :
			code = "PROD";
			break;
		case Informatique :
			code = "INFO";
			break;
		case Achat :
			code = "ACH";
			break;
		default:
			throw new IllegalStateException("Invalid service value: " + service);
		}
		return code;
	}
}
