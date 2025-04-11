package com.petr.postcode_api.auth;
import java.util.List;

import com.petr.postcode_api.user.Role;

public class LoginResponse {
    private String jwtToken;
    private String username;
    private List<Role> roles;

    
    public LoginResponse(String jwtToken, List<Role> roles, String username) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.roles = roles;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
