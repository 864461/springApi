package com.example.demoapi.application.Controllers;

import com.example.demoapi.application.Services.PasswordResetService;
import com.example.demoapi.domain.Dto.UserDto;
import com.example.demoapi.domain.Entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResetPassword {

    private PasswordResetService passwordResetService;

    public UserResetPassword(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/reset-my-password")
    public ResponseEntity<?> resetMyPassword(@AuthenticationPrincipal User user,@RequestBody UserDto newPassword) {
        passwordResetService.resetPassword(user,newPassword);
        return ResponseEntity.ok().build();
    }
}
