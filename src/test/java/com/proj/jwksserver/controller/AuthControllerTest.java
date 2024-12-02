package com.proj.jwksserver.controller;

import com.proj.jwksserver.service.JWKSService;
import com.proj.jwksserver.service.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JWKSService jwksService;

    @Autowired
    private JWTService jwtService;

    @Test
    void testAuthEndpointValidJWT() throws Exception {
        mockMvc.perform(post("/auth"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString(".")));
    }

    @Test
    void testAuthEndpointExpiredJWT() throws Exception {
        mockMvc.perform(post("/auth").param("expired", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString(".")));
    }
}
