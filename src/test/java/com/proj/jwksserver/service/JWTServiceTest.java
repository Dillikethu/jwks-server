package com.proj.jwksserver.service;

import com.proj.jwksserver.model.KeyMetadata;
import com.proj.jwksserver.util.RSAKeyGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JWTServiceTest {

    private JWTService jwtService;
    private KeyMetadata key;

    @BeforeEach
    void setup() throws Exception {
        jwtService = new JWTService();
        key = new KeyMetadata();
        key.setKid("test-kid");
        key.setKeyPair(RSAKeyGenerator.generateKeyPair());
        key.setExpiry(Instant.now().plusSeconds(3600));
    }

    @Test
    void testCreateJWT() {
        String jwt = jwtService.createJWT(key, false);
        assertNotNull(jwt, "JWT should not be null for valid keys");
    }

    @Test
    void testCreateExpiredJWT() {
        String jwt = jwtService.createJWT(key, true);
        assertNotNull(jwt, "Expired JWT should still be created");
    }
}
