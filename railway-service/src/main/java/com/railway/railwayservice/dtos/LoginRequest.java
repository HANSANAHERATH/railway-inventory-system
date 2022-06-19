package com.railway.railwayservice.dtos;

import lombok.Data;

/**
 * The type Login request.
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
}