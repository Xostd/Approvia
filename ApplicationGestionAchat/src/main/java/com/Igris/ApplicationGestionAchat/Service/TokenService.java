package com.Igris.ApplicationGestionAchat.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Igris.ApplicationGestionAchat.Entity.Token;
import com.Igris.ApplicationGestionAchat.Repository.TokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public List<Token> getAllTokensByUser(String matricule) {
        return tokenRepository.findAllTokensByUser(matricule);
    }

    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    public Optional<Token> getByToken(String token) {
        return tokenRepository.findByToken(token);
    }

}
