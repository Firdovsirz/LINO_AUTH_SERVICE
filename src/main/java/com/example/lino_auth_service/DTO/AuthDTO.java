package com.example.lino_auth_service.DTO;
import java.security.Timestamp;

public class AuthDTO {
    private String email;
    private String authProvider;
    private Timestamp lastLogin;
    private Integer role;
    private Integer userSubs;

    public void authSigninDTO(String email, String authProvider, Timestamp lastLogin, Integer userSubs, Integer role) {
        this.email = email;
        this.authProvider = authProvider;
        this.lastLogin = lastLogin;
        this.role = role;
        this.userSubs = userSubs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getUserSubs() {
        return userSubs;
    }

    public void setUserSubs(Integer userSubs) {
        this.userSubs = userSubs;
    }
}