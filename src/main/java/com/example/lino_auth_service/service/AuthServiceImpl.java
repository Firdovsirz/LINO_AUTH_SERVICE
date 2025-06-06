package com.example.lino_auth_service.service;
import com.example.lino_auth_service.DTO.AuthRepo;
import com.example.lino_auth_service.entity.Auth;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepo authRepo;

    public AuthServiceImpl(AuthRepo authRepo) {
        this.authRepo = authRepo;
    }

    @Override
    public Optional<Auth> authenticate(String email, String password) {
        return null;
    }

    @Override
    public String findByEmail(String email) {
        authRepo.findByEmail(email);
        if (authRepo.findByEmail(email) == null) {
            return null;
        } else {
            return authRepo.findByEmail(email).getEmail();
        }
    }
}
