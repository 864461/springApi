package com.example.demoapi.infrastructure.Persitence.Repositories;

import com.example.demoapi.domain.Entities.SecretMessage;
import com.example.demoapi.domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretMessageRepository extends JpaRepository<SecretMessage,Long> {
    SecretMessage findByName(String name);
}
