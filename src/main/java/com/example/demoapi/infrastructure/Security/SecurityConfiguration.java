package com.example.demoapi.infrastructure.Security;

import com.example.demoapi.infrastructure.Persitence.Repositories.jwtTokenRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;






@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    private final UserAuth authenticationProvider;

    private final jwtTokenRepositories tokenRepository;

    @Autowired
    private MyUserDetailsService  userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public SecurityConfiguration(UserAuth authenticationProvider, jwtTokenRepositories tokenRepository ) {
        this.authenticationProvider = authenticationProvider;
        this.tokenRepository = tokenRepository;
    }



    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
//                .addFilterBefore(new UsernamePasswordFilter(authenticationProvider,authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(authenticationProvider, tokenRepository),UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(new LogOutFilter(authenticationProvider,tokenRepository),JwtFilter.class)
                .authenticationProvider(authenticationProvider())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests((requests) -> requests.requestMatchers(HttpMethod.POST, "/sign-in", "/sign-up").permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }

}
