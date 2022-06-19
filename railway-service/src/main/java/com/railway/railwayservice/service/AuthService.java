package com.railway.railwayservice.service;

import com.railway.railwayservice.dtos.JwtResponse;
import com.railway.railwayservice.dtos.LoginRequest;
import com.railway.railwayservice.dtos.SignupRequest;

/**
 * The interface Auth service.
 */
public interface AuthService {
    /**
     * Authenticate user jwt response.
     *
     * @param loginRequest the login request
     * @return the jwt response
     */
    JwtResponse authenticateUser(LoginRequest loginRequest);

    /**
     * Register user.
     *
     * @param signUpRequest the sign up request
     */
    void registerUser(SignupRequest signUpRequest);

    /**
     * Sign out string.
     *
     * @param loginRequest the login request
     * @param token        the token
     * @return the string
     */
    String signOut(LoginRequest loginRequest, String token);
}
