package com.example.demoapi.domain.Dto;

public class SecreteMessageDto {
    private Long id;

    private String name;

    private String secretMessage;


    public SecreteMessageDto(Long id, String name, String secretMessage) {
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
