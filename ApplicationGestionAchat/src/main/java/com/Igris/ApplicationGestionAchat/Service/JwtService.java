package com.Igris.ApplicationGestionAchat.Service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Entity.User.User;
import com.Igris.ApplicationGestionAchat.Service.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
//this class is mean for jwt token generation and payload extraction
	private final String SECRET_KEY="9e80fda66fa07cdc6d58c55949e92044db2e6f27cb259f484e7add3c2fdf7562";
    private final TokenService tokenService;

	public String generateToken(User user) {
		String token = Jwts
				.builder()
				.subject(user.getMatricule())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+(24*60*60*1000)))
				.signWith(getSingInKey())//generate the signature part in jwt token based on the secret key
				.compact();
		return token;
	}
	
	private SecretKey getSingInKey() {
		byte[] keyBytes= Decoders.BASE64URL.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	// this methode simply extracts the payload part from the jwt token 
	// which will be processed later on
	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(getSingInKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	// has to be private for security purpose to not give access to get any other data
	private <T> T extractClaim(String token, Function<Claims,T> resolver) {
		Claims claims= extractAllClaims(token);
		return resolver.apply(claims);
	}
	
	public String extractMatricule(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}
	public boolean isValid(String token, UserDetails user) {
        String username = extractMatricule(token);

        boolean isvalidToken = tokenService
                .getByToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);

        return (username.equals(user.getUsername())) && !isTokenExpired(token) && isvalidToken;
    }
}
