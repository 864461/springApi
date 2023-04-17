package com.example.demoapi.domain.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class SecretMessage {

    @Id
    @GeneratedValue()
    private Long id;

    private String name;

    private String secretMessage;

    public SecretMessage() {}

    public SecretMessage(long id, String name, String secretMessage) {
        super();
        this.id = id;
        this.name = name;
        this.secretMessage = secretMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecretMessage() {
        return secretMessage;
    }

    public void setSecretMessage(String secretMessage) {
        this.secretMessage = secretMessage;
    }
}
