package com.proj.jwksserver.util;

import org.junit.jupiter.api.Test;

import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RSAKeyGeneratorTest {

    @Test
    void testGenerateKeyPair() throws Exception {
        KeyPair keyPair = RSAKeyGenerator.generateKeyPair();
        assertNotNull(keyPair.getPublic(), "Public key should not be null");
        assertNotNull(keyPair.getPrivate(), "Private key should not be null");
    }
}
