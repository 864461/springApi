package com.example.demoapi.infrastructure.Security;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demoapi.domain.Dto.UserDto;
import com.example.demoapi.domain.Entities.User;
import com.example.demoapi.domain.Entities.jwtToken;
import com.example.demoapi.infrastructure.Persitence.Repositories.jwtTokenRepositories;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class UserAuth {
    @Value("${jwt.secret}")
    private String secretKey;


    private final AuthService authenticationService;
    private final jwtTokenRepositories tokenRepository;

    public UserAuth(AuthService authenticationService, jwtTokenRepositories tokenRepository) {
        this.authenticationService = authenticationService;
        this.tokenRepository = tokenRepository;
    }



    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 4000000);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer(email)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }


    public Authentication validateToken(String token) {
        jwtToken savedToken = tokenRepository.findByToken(token);
        if(savedToken.expired){
            throw  new RuntimeException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        User user = authenticationService.findByEmail(decoded.getIssuer());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public UsernamePasswordAuthenticationToken  validateCredentials(UserDto credentialsDto) {
        UserDto user = authenticationService.authenticate(credentialsDto);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword(), authorities);
    }
}
