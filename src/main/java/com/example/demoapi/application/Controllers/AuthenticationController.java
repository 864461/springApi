package com.example.demoapi.application.Controllers;


import com.example.demoapi.application.Services.SignInService;
import com.example.demoapi.application.Services.SignUpService;
import com.example.demoapi.domain.Dto.UserDto;
import com.example.demoapi.domain.Entities.User;
import com.example.demoapi.domain.Entities.jwtToken;
import com.example.demoapi.domain.Exception.MissingFieldException;
import com.example.demoapi.domain.ValidateUtil.AuthenticationResponse;
import com.example.demoapi.domain.ValidateUtil.UtilValidate;
import com.example.demoapi.infrastructure.Persitence.Repositories.jwtTokenRepositories;
import com.example.demoapi.infrastructure.Security.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
public class AuthenticationController {


    private SignUpService signUpService;
    private SignInService signInService;






    public AuthenticationController(SignUpService signUpService,SignInService signInService) {
        this.signUpService = signUpService;
        this.signInService = signInService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserDto user) {
        signUpService.signUp(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(@AuthenticationPrincipal UserDto user) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody UserDto user) {
        AuthenticationResponse authenticationResponse = signInService.authenticate(user);
        return ResponseEntity.ok(authenticationResponse);
    }

}
