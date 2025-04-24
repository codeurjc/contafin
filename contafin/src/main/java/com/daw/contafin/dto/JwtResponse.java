package com.daw.contafin.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private UserDto user;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, UserDto user, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.user = user;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}
