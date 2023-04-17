package com.example.demoapi.infrastructure.Security;


import com.example.demoapi.domain.Entities.jwtToken;
import com.example.demoapi.infrastructure.Persitence.Repositories.jwtTokenRepositories;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserAuth userAuthenticationProvider;
    private final jwtTokenRepositories tokenRepository;

    public JwtFilter(UserAuth userAuthenticationProvider, jwtTokenRepositories tokenRepository) {
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);


        if (header != null) {
            String[] authElements = header.split(" ");

            if (authElements.length == 2
                    && "Bearer".equals(authElements[0])) {
                try {
                        jwtToken activeToken =  tokenRepository.findByToken(authElements[1]);
                        if(!activeToken.isExpired()) {
                            SecurityContextHolder.getContext().setAuthentication(
                                    userAuthenticationProvider.validateToken(authElements[1]));
                        }

                    if (("/sign-out".equals(httpServletRequest.getServletPath()) || "/reset-my-password".equals(httpServletRequest.getServletPath()))
                            && HttpMethod.POST.matches(httpServletRequest.getMethod())) {
                        try {
                            jwtToken activeToken1 =  tokenRepository.findByToken(authElements[1]);
                            activeToken.setExpired(true);
                            tokenRepository.save(activeToken);
                        } catch (RuntimeException e) {
                            SecurityContextHolder.clearContext();
                            throw e;

                        }
                    }
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


}