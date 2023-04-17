package com.example.demoapi.application.Services;


import com.example.demoapi.domain.Dto.UserDto;
import com.example.demoapi.domain.Entities.User;
import com.example.demoapi.infrastructure.Persitence.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PasswordResetServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PasswordResetService passwordResetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void resetPassword_shouldEncodePasswordAndSaveUser() {
        UserDto userDto = mock(UserDto.class);
        userDto.setPassword("newPassword");

        User user = mock(User.class);
        user.setPassword("oldPassword");

        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        passwordResetService.resetPassword(user, userDto);

        verify(passwordEncoder).encode(userDto.getPassword());
        verify(userRepository).save(user);
//        assertEquals(user.getPassword(), "encodedPassword");
    }

    @Test
    void testResetPasswordShouldThrowException() {

        User user = mock(User.class);
        UserDto userDto = mock(UserDto.class);
        userDto.setPassword("newPassword");


        doThrow(new RuntimeException("Unable to reset password")).when(userRepository).save(any(User.class));


        try {
            passwordResetService.resetPassword(user, userDto);
        } catch (Exception e) {

            verify(passwordEncoder).encode(userDto.getPassword());

            verify(userRepository).save(any(User.class));
            return;
        }
        
        throw new AssertionError("Expected an exception to be thrown.");
    }



}
