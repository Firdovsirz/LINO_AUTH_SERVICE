package com.example.lino_auth_service.DTO;

import com.example.lino_auth_service.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<Auth, Long> {
    Auth findByEmail(String email);
}
