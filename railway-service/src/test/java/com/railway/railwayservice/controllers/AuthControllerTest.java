package com.railway.railwayservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.railway.railwayservice.dtos.CreateInventoryDto;
import com.railway.railwayservice.dtos.JwtResponse;
import com.railway.railwayservice.dtos.LoginRequest;
import com.railway.railwayservice.dtos.SignupRequest;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.service.AuthService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerTest {

    @MockBean
    private AuthService authService;

    private AuthController authController;

    @BeforeEach
    void setUp() {
        authController = new AuthController(authService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void authenticateUser() throws Exception {
        when(this.authService.authenticateUser(any())).thenReturn(new JwtResponse());

        LoginRequest loginRequest = new LoginRequest();

        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /*@Test
    void registerUser() throws Exception {
        //AuthController mockAuthController = mock(AuthController.class);
        SignupRequest signupRequest = new SignupRequest();
        //doNothing().when(mockAuthController).registerUser(any());

        String content = (new ObjectMapper()).writeValueAsString(signupRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    void signOut() throws Exception {
        String res = "";
        when(this.authService.signOut(any(),any())).thenReturn(res);

        LoginRequest loginRequest = new LoginRequest();

        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/signout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .header("Bearer ","sdsdsdsd");
        MockMvcBuilders.standaloneSetup(this.authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }*/
}