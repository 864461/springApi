package com.example.demoapi.infrastructure.Security;

import com.example.demoapi.domain.Dto.UserDto;
import com.example.demoapi.domain.Entities.User;
import com.example.demoapi.domain.Exception.UserNotFoundException;
import com.example.demoapi.infrastructure.Persitence.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public AuthService(PasswordEncoder passwordEncoder,UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public UserDto authenticate(UserDto user) {
        User dbUser = userRepository.findByEmail(user.getEmail());
        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            return  user;
        }
        throw new UserNotFoundException("User Not Found");
    }


    public User findByEmail(String email) {
       return userRepository.findByEmail(email);
    }
}
