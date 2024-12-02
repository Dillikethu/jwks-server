package com.proj.jwksserver.controller;

import com.proj.jwksserver.service.JWKSService;
import com.proj.jwksserver.util.RSAKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class JWKSController {
    @Autowired
    private JWKSService jwksService;

    // JWKS endpoint that serves the public keys in JWKS format.
    @GetMapping("/jwks")
    public Map<String, Object> getJWKS() {
        return Map.of(
                "keys", jwksService.getActiveKeys().stream().map(key -> Map.of(
                        "kid", key.getKid(),
                        "kty", "RSA",
                        "alg", "RS256",
                        "use", "sig",
                        "n", RSAKeyGenerator.getPublicKeyAsPEM(key.getKeyPair().getPublic())
                )).collect(Collectors.toList())
        );
    }
}
