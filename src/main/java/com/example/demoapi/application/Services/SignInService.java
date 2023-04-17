package com.example.demoapi.application.Services;


import com.example.demoapi.domain.Dto.UserDto;
import com.example.demoapi.domain.Entities.User;
import com.example.demoapi.domain.Entities.jwtToken;
import com.example.demoapi.domain.ValidateUtil.AuthenticationResponse;
import com.example.demoapi.infrastructure.Persitence.Repositories.UserRepository;
import com.example.demoapi.infrastructure.Persitence.Repositories.jwtTokenRepositories;
import com.example.demoapi.infrastructure.Security.UserAuth;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import org.springframework.security.authentication.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
//@RequiredArgsConstructor
public class SignInService  {

    private final UserRepository repository;
    private final jwtTokenRepositories tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuth userAuth;

//    @Autowired
    private final AuthenticationManager authenticationManager;

    public SignInService(UserRepository repository, jwtTokenRepositories tokenRepository, PasswordEncoder passwordEncoder, UserAuth userAuth, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.userAuth = userAuth;
        this.authenticationManager = authenticationManager;
    }



    public AuthenticationResponse authenticate(UserDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail());
        String jwtToken = userAuth.createToken(user.getEmail());
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    void saveUserToken(User user, String jwtToken){
        jwtToken newToken = new jwtToken();
        newToken.setToken(jwtToken);
        newToken.setExpired(false);
        tokenRepository.save(newToken);

    }

}
