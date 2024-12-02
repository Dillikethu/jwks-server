package com.proj.jwksserver.service;

import com.proj.jwksserver.model.KeyMetadata;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {
    public String createJWT(KeyMetadata key, boolean expired) {
        PrivateKey privateKey = key.getKeyPair().getPrivate();
        return Jwts.builder()
                .setHeaderParam("kid", key.getKid())
                .setClaims(Map.of("sub", "fake-user"))
                .setIssuedAt(new Date())
                .setExpiration(Date.from(expired ? key.getExpiry().minusSeconds(3600) : key.getExpiry()))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }
}
