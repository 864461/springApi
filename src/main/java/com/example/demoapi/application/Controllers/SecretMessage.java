package com.example.demoapi.application.Controllers;

import com.example.demoapi.application.Services.SecretMessageService;
import com.example.demoapi.domain.Dto.UserDto;
import com.example.demoapi.domain.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretMessage {

    private SecretMessageService  secretMessageService;

    public SecretMessage(SecretMessageService secretMessageService) {
        this.secretMessageService = secretMessageService;
    }

    @GetMapping("/secret-message")
    public ResponseEntity  getSecreteMessage(@AuthenticationPrincipal UserDto user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(secretMessageService.getMessage(authentication));
    }
}
