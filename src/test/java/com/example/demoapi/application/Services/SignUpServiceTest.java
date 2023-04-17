package com.example.demoapi.application.Services;




import com.example.demoapi.domain.Dto.UserDto;
import com.example.demoapi.domain.Entities.User;
import com.example.demoapi.domain.Exception.DuplicateUserException;
import com.example.demoapi.domain.Exception.MissingFieldException;
import com.example.demoapi.infrastructure.Persitence.Repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class SignUpServiceTest {


    private SignUpService signUpService;


    private UserRepository userRepositoryMock;


    private PasswordEncoder passwordEncoderMock;

    @BeforeEach
    public void setUp() {
        userRepositoryMock = mock(UserRepository.class);
        passwordEncoderMock = mock(PasswordEncoder.class);
        signUpService = new SignUpService(userRepositoryMock, passwordEncoderMock);
    }

    @Test
    public void testSignUp_WithMissingEmail() {
        UserDto user = mock(UserDto.class);
        user.setPassword("password");

        MissingFieldException exception = Assertions.assertThrows(MissingFieldException.class, () -> {
            signUpService.signUp(user);
        });

        Assertions.assertEquals("Email or Password is Missing", exception.getMessage());
    }

    @Test
    public void testSignUp_WithMissingPassword() {
        UserDto user = mock(UserDto.class);
        user.setEmail("user@example.com");

        MissingFieldException exception = Assertions.assertThrows(MissingFieldException.class, () -> {
            signUpService.signUp(user);
        });

        Assertions.assertEquals("Email or Password is Missing", exception.getMessage());
    }

    @Test
    public void testSignUp_WithDuplicateUser() {
        UserDto user = new UserDto("","","test@test.com","11111");

        User existingUser = new User();
        existingUser.setEmail("example@example.com");

        Mockito.when(userRepositoryMock.findByEmail(user.getEmail())).thenReturn(existingUser);

        DuplicateUserException exception = Assertions.assertThrows(DuplicateUserException.class, () -> {
            signUpService.signUp(user);
        });

        Assertions.assertEquals("User Already Exist", exception.getMessage());
    }

    @Test
    public void testSignUp_Success() {
        UserDto user = new UserDto("John","doe","test@test.com","11111");

        Mockito.when(userRepositoryMock.findByEmail(user.getEmail())).thenReturn(null);

        signUpService.signUp(user);

        Mockito.verify(userRepositoryMock, Mockito.times(1)).save(Mockito.any(User.class));

    }
}
