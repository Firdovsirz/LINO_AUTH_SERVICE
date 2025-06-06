package com.example.lino_auth_service.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "\"user\"")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "auth_provider", nullable = false)
    private Integer authProvider; // 0 - local, 1 - google, 2 - apple

    @Column(name = "is_frozen")
    private Boolean isFrozen;

    @Column(name = "last_login")
    private String lastLogin;

    @Column(name = "role", nullable = false)
    private Integer role; // 1 - dev (Firdovsi & Karam), 2 - Teacher, 3 - user

    @Column(name = "user_subs")
    private Integer userSubs; //  0 - simple package, 1 - gold package, 2 - premium package

    @Column(name = "otp")
    private Integer otp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Integer getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(Integer authProvider) {
        this.authProvider = authProvider;
    }

    public Boolean getFrozen() {
        return isFrozen;
    }

    public void setFrozen(Boolean froozen) {
        isFrozen = froozen;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
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

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", providerId='" + providerId + '\'' +
                ", authProvider=" + authProvider +
                ", isFroozen=" + isFrozen +
                ", lastLogin='" + lastLogin + '\'' +
                ", role=" + role +
                ", userSubs=" + userSubs +
                ", otp=" + otp +
                '}';
    }
}
