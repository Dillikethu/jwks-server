package com.proj.jwksserver.controller;

import com.proj.jwksserver.service.JWKSService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(JWKSController.class)
class JWKSControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JWKSService jwksService;

    @Test
    void testGetJWKS() throws Exception {
        jwksService.createNewKey(); // Create a key for the test

        mockMvc.perform(MockMvcRequestBuilders.get("/jwks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.keys").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.keys[0].kid").exists());
    }
}
