package com.example.lino_auth_service.service;

import com.example.lino_auth_service.entity.Auth;

import java.util.Optional;

public interface AuthService {
    Optional<Auth> authenticate(String email, String password);
    String findByEmail(String email);
}
