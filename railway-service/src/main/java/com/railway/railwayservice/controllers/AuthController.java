package com.railway.railwayservice.controllers;

import com.railway.railwayservice.dtos.JwtResponse;
import com.railway.railwayservice.dtos.LoginRequest;
import com.railway.railwayservice.dtos.SignupRequest;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.enums.ActiveStatus;
import com.railway.railwayservice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Auth controller.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private static final String USER_REGISTER_SUCCESS = "User registered successfully!";

    private final AuthService authService;

    /**
     * Authenticate user response entity.
     *
     * @param loginRequest the login request
     * @return the response entity
     */
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    /**
     * Register user response entity.
     *
     * @param signUpRequest the sign up request
     * @return the response entity
     */
    @PostMapping("/signup")
    public ResponseEntity<ResponseWrapperDto> registerUser(@RequestBody SignupRequest signUpRequest) {
        authService.registerUser(signUpRequest);
        return ResponseEntity.ok(new ResponseWrapperDto<>(ActiveStatus.ACTIVE.getValue(), USER_REGISTER_SUCCESS, null));
    }

    /**
     * Sign out response entity.
     *
     * @param loginRequest the login request
     * @param token        the token
     * @return the response entity
     */
    @PostMapping("/signout")
    public ResponseEntity<?> signOut(@RequestBody LoginRequest loginRequest, @RequestHeader(value = "Authorization", required = true) String token) {
        String res = authService.signOut(loginRequest, token);
        if (null != res) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.internalServerError().body(null);
    }
}
