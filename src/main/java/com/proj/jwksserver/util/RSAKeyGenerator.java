package com.proj.jwksserver.util;

import java.security.*;
import java.util.Base64;

public class RSAKeyGenerator {
    // Generate RSA Key Pair
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    // Get Public Key in PEM Fromat
    public static String getPublicKeyAsPEM(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
}
