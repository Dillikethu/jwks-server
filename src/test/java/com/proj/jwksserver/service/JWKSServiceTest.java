package com.proj.jwksserver.service;

import com.proj.jwksserver.model.KeyMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JWKSServiceTest {

    private JWKSService jwksService;

    @BeforeEach
    void setup() {
        jwksService = new JWKSService();
    }

    @Test
    void testGetActiveKeys() throws Exception {
        KeyMetadata activeKey = jwksService.createNewKey();
        KeyMetadata expiredKey = new KeyMetadata();
        expiredKey.setKid("expired");
        expiredKey.setExpiry(Instant.now().minusSeconds(3600));
        jwksService.getActiveKeys().add(expiredKey);

        List<KeyMetadata> activeKeys = jwksService.getActiveKeys();
        assertEquals(1, activeKeys.size(), "Only one key should be active");
        assertEquals(activeKey.getKid(), activeKeys.get(0).getKid());
    }
}
