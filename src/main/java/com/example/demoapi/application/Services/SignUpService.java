package com.example.demoapi.application.Services;


import com.example.demoapi.domain.Dto.UserDto;
import com.example.demoapi.domain.Entities.User;
import com.example.demoapi.domain.Exception.DuplicateUserException;
import com.example.demoapi.domain.Exception.MissingFieldException;
import com.example.demoapi.domain.ValidateUtil.UtilValidate;
import com.example.demoapi.infrastructure.Persitence.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    public SignUpService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean UserExist(String email) {
        return UtilValidate.isEmpty(userRepository.findByEmail(email));
    }

    public void signUp(UserDto user)  throws DuplicateUserException {
        validateParams(user);
        if (!UtilValidate.isEmpty(userRepository.findByEmail(user.getEmail()))) {
            throw new DuplicateUserException("User Already Exist");
        }
        encodePassword(user);
        User userRepo = new User();
        userRepo.setFirstName(user.getFirstname());
        userRepo.setLastName(user.getLastname());
        userRepo.setEmail(user.getEmail());
        userRepo.setPassword(user.getPassword());
        userRepository.save(userRepo);
    }
    protected void validateParams(UserDto user){
        if (UtilValidate.isEmpty(user.getEmail()) || UtilValidate.isEmpty(user.getPassword())) {
            throw new MissingFieldException("Email or Password is Missing");
        }
    }

    public void encodePassword(UserDto user){
        String password = user.getPassword();
        user.setPassword( passwordEncoder.encode(password));
    }

}
