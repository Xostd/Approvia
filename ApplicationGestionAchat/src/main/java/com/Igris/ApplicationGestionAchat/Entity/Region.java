package com.Igris.ApplicationGestionAchat.Entity;

public enum Region {
	Tunis,
	Sfax,
	Gafsa,
	Gabes;
	
	public static String getCode(Region region) throws IllegalStateException {
		String code;
		switch(region) {
		case Tunis :
			code="TUN";
			break;
		case Sfax :
			code="SFX";
			break;
		case Gafsa :
			code="GFS";
			break;
		case Gabes :
			code="GBS";
			break;
		default :
			throw new IllegalStateException("Invalid region value: " + region);
		}
		return code;
	}
}
