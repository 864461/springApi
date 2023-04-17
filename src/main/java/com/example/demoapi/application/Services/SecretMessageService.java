package com.example.demoapi.application.Services;

import com.example.demoapi.domain.Dto.SecreteMessageDto;
import com.example.demoapi.domain.Dto.UserDto;
import com.example.demoapi.domain.Entities.SecretMessage;
import com.example.demoapi.domain.Entities.User;
import com.example.demoapi.domain.Exception.DuplicateUserException;
import com.example.demoapi.domain.ValidateUtil.UtilValidate;
import com.example.demoapi.infrastructure.Persitence.Repositories.SecretMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SecretMessageService {

    private SecretMessageRepository messageRepository;

    @Autowired
    public SecretMessageService(SecretMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public  String getMessage(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        String fullname = "";
        if (principal instanceof User) {
            User user = (User) principal;
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            fullname = firstName + " " + lastName;
        }
        SecretMessage secret = messageRepository.findByName(fullname);
        if(!UtilValidate.isEmpty(secret)) {
            String secretMessage = secret.getSecretMessage();
            return secretMessage;
        } else{
            return "User has no secret set";
        }
    }
}
