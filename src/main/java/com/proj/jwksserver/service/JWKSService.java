package com.proj.jwksserver.service;

import com.proj.jwksserver.model.KeyMetadata;
import com.proj.jwksserver.util.RSAKeyGenerator;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JWKSService {
    private final List<KeyMetadata> keys = new ArrayList<>();

    public KeyMetadata createNewKey() throws Exception {
        KeyPair keyPair = RSAKeyGenerator.generateKeyPair();
        KeyMetadata keyMetadata = new KeyMetadata();
        keyMetadata.setKid(UUID.randomUUID().toString());
        keyMetadata.setKeyPair(keyPair);
        keyMetadata.setExpiry(Instant.now().plusSeconds(3600));
        keys.add(keyMetadata);
        return keyMetadata;
    }

    public List<KeyMetadata> getActiveKeys() {
        Instant now = Instant.now();
        return keys.stream()
                .filter(key -> key.getExpiry().isAfter(now))
                .collect(Collectors.toList());
    }
}
