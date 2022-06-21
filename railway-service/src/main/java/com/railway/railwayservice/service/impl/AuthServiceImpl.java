package com.railway.railwayservice.service.impl;

import com.railway.railwayservice.Exceptions.InputNotValidException;
import com.railway.railwayservice.Exceptions.RuntimeExceptionHere;
import com.railway.railwayservice.dtos.JwtResponse;
import com.railway.railwayservice.dtos.LoginRequest;
import com.railway.railwayservice.dtos.SignupRequest;
import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import com.railway.railwayservice.entity.Role;
import com.railway.railwayservice.entity.UserDetails;
import com.railway.railwayservice.enums.ActiveStatus;
import com.railway.railwayservice.enums.ERole;
import com.railway.railwayservice.repository.BlackListTokenRepository;
import com.railway.railwayservice.repository.RoleRepository;
import com.railway.railwayservice.repository.UserDetailsRepository;
import com.railway.railwayservice.security.JwtUtils;
import com.railway.railwayservice.security.service.UserDetailsImpl;
import com.railway.railwayservice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Auth service.
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final String USER_NAME_EXISTS = "Username is already taken!";
    private static final String EMAIL_EXISTS = "Email is already in use!";
    private static final String ROLE_NOT_FOUND = "Role is not found.";
    private static final String LOGGED_OUT = "Logged out user ";

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final BlackListTokenRepository blackListTokenRepository;

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(userNamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId().toString(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public void registerUser(SignupRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            throw new InputNotValidException(new ResponseWrapperDto<>(ActiveStatus.INACTIVE.getValue(), USER_NAME_EXISTS, null));
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            throw new InputNotValidException(new ResponseWrapperDto<>(ActiveStatus.INACTIVE.getValue(), EMAIL_EXISTS, null));
        }

        UserDetails user = new UserDetails(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeExceptionHere(ROLE_NOT_FOUND));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeExceptionHere(ROLE_NOT_FOUND));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeExceptionHere(ROLE_NOT_FOUND));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeExceptionHere(ROLE_NOT_FOUND));
                        roles.add(userRole);
                        break;
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public String signOut(LoginRequest loginRequest, String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            String tokenWithoutBearer = token.substring(7);
            String username = jwtUtils.getUserNameFromJwtToken(tokenWithoutBearer);
            blackListTokenRepository.saveToken(username, tokenWithoutBearer);
            return LOGGED_OUT + username;
        }

        return null;
    }
}
