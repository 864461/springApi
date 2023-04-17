package com.example.demoapi.application.Services;

import com.example.demoapi.domain.Entities.jwtToken;
import com.example.demoapi.infrastructure.Persitence.Repositories.jwtTokenRepositories;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SignOutService  {
//
//    private final jwtTokenRepositories tokenRepository;
//
//    public SignOutService(jwtTokenRepositories tokenRepository) {
//        this.tokenRepository = tokenRepository;
//    }
//
//
//    public void logout(
//            Authentication authentication
//    ) {
//        Object principal = authentication.getPrincipal();
//        String email = principal.getEmail();
////        jwtToken storedToken = tokenRepository.findByToken(jwt);
////        if (storedToken != null) {
////            storedToken.setExpired(true);
////            tokenRepository.save(storedToken);
////            SecurityContextHolder.clearContext();
////        }
//    }
//
//
//}