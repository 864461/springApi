package com.example.demoapi.application.Services;

import com.example.demoapi.domain.Dto.UserDto;
import com.example.demoapi.domain.Entities.User;
import com.example.demoapi.infrastructure.Persitence.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public PasswordResetService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void resetPassword(User user, UserDto userDto){
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);

    }


}
