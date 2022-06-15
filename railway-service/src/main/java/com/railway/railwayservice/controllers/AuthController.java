package com.railway.railwayservice.controllers;

import com.railway.railwayservice.dtos.JwtResponse;
import com.railway.railwayservice.dtos.LoginRequest;
import com.railway.railwayservice.dtos.SignupRequest;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.Role;
import com.railway.railwayservice.entity.UserDetails;
import com.railway.railwayservice.enums.ERole;
import com.railway.railwayservice.repository.BlackListTokenRepository;
import com.railway.railwayservice.repository.RoleRepository;
import com.railway.railwayservice.repository.UserDetailsRepository;
import com.railway.railwayservice.security.JwtUtils;
import com.railway.railwayservice.security.service.UserDetailsImpl;
import com.railway.railwayservice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseWrapperDto> registerUser(@RequestBody SignupRequest signUpRequest) {
        authService.registerUser(signUpRequest);
        return ResponseEntity.ok(new ResponseWrapperDto<>(true,"User registered successfully!",null));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signOut(@RequestBody LoginRequest loginRequest, @RequestHeader(value = "Authorization", required = true) String token) {
        String res = authService.signOut(loginRequest, token);
        if(null != res){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.internalServerError().body(null);
    }
}
