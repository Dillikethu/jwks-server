package com.proj.jwksserver.model;

import lombok.Getter;
import lombok.Setter;

import java.security.KeyPair;
import java.time.Instant;

@Getter
@Setter
public class KeyMetadata {
    private String kid;
    private KeyPair keyPair;
    private Instant expiry;
}
