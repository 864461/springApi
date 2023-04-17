package com.example.demoapi.application.Services;




import com.example.demoapi.domain.Dto.UserDto;
import com.example.demoapi.domain.Entities.SecretMessage;
import com.example.demoapi.domain.Entities.User;
import com.example.demoapi.infrastructure.Persitence.Repositories.SecretMessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
public class SecretMessageServiceTest {

    private SecretMessageRepository messageRepositoryMock;
    private SecretMessageService secretMessageService;

    @BeforeEach
    public void setUp() {
        messageRepositoryMock = Mockito.mock(SecretMessageRepository.class);
        secretMessageService = new SecretMessageService(messageRepositoryMock);
    }

    @Test
    public void testGetMessage_UserWithSecret() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        SecretMessage secret = new SecretMessage();
        secret.setSecretMessage("This is a secret message");
        secret.setName("John Doe");

        Mockito.when(messageRepositoryMock.findByName("John Doe")).thenReturn(secret);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(user);

        String result = secretMessageService.getMessage(authentication);

        Assertions.assertEquals("This is a secret message", result);
    }
    @Test
    public void testGetMessage_UserWithoutSecret() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        Mockito.when(messageRepositoryMock.findByName("John Doe")).thenReturn(null);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(user);

        String result = secretMessageService.getMessage(authentication);

        Assertions.assertEquals("User has no secret set", result);
    }
}
