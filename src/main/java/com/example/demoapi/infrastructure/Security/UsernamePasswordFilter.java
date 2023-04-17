//package com.example.demoapi.infrastructure.Security;
//
//import com.example.demoapi.domain.Dto.UserDto;
//import com.example.demoapi.domain.Entities.User;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolderStrategy;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//@Component
//public class UsernamePasswordFilter extends OncePerRequestFilter {
//
//    private static final ObjectMapper MAPPER = new ObjectMapper();
//
//    private final UserAuth userAuthenticationProvider;
//    private final AuthenticationManager authenticationManager;
//
//
//    public UsernamePasswordFilter(UserAuth userAuthenticationProvider,AuthenticationManager authenticationManager) {
//        this.userAuthenticationProvider = userAuthenticationProvider;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest httpServletRequest,
//            HttpServletResponse httpServletResponse,
//            FilterChain filterChain) throws ServletException, IOException {
//
//        if ("/sign-in".equals(httpServletRequest.getServletPath())
//                && HttpMethod.POST.matches(httpServletRequest.getMethod())) {
//
////            UserDto user = MAPPER.readValue(httpServletRequest.getInputStream(), UserDto.class);
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.readTree(httpServletRequest.getInputStream());
//            String email = jsonNode.get("email").asText();
//            String password = jsonNode.get("password").asText();
//            UserDto user = new UserDto("","",email,password);
//
////            try {
//
//            UsernamePasswordAuthenticationToken  authRequest =  userAuthenticationProvider.validateCredentials(user);
//
//                // authenticate the user
//                Authentication authResult = authenticationManager.authenticate(authRequest);
//
//                // store the authenticated user in the security context
//                SecurityContextHolder.getContext().setAuthentication(authResult);
//
//
//
////                SecurityContextHolder.getContext().setAuthentication(
////                        userAuthenticationProvider.validateCredentials(user));
////            } catch (RuntimeException e) {
////                SecurityContextHolder.clearContext();
////                throw e;
////
////            }
////            filterChain.doFilter(httpServletRequest, httpServletResponse);
//        }
//
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}