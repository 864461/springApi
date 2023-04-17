package com.example.demoapi.infrastructure.Persitence.Repositories;

import com.example.demoapi.domain.Entities.SecretMessage;
import com.example.demoapi.domain.Entities.jwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface jwtTokenRepositories extends JpaRepository<jwtToken,Long> {

    jwtToken findByToken(String token);
}
