package com.railway.railwayservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * The type Signup request.
 */
@Data
@AllArgsConstructor
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private Set<String> role;
}