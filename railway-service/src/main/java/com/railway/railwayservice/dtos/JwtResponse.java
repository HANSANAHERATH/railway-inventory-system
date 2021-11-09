package com.railway.railwayservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {

    private String token;
    private String type = "Basic";
    private String username;
    private String password;
    private List roles;
}
