package com.proj.jwksserver.controller;

import com.proj.jwksserver.model.KeyMetadata;
import com.proj.jwksserver.service.JWKSService;
import com.proj.jwksserver.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private JWKSService jwksService;

    @Autowired
    private JWTService jwtService;

    // endpoint that returns an unexpired, signed JWT on a POST request.
    @PostMapping("/auth")
    public String authenticate(@RequestParam(required = false) boolean expired) throws Exception {
        KeyMetadata key = expired ? jwksService.getActiveKeys().get(0) : jwksService.createNewKey();
        return jwtService.createJWT(key, expired);
    }
}
