//package com.example.demoapi.application.Controllers;
//
//import com.example.demoapi.application.Services.SecretMessageService;
//import com.example.demoapi.application.Services.SignInService;
//import com.example.demoapi.application.Services.SignUpService;
//import com.example.demoapi.domain.Dto.UserDto;
//import com.example.demoapi.domain.Exception.DuplicateUserException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(AuthenticationController.class)
//@EnableWebMvc
//public class AuthenticationControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean()
//    private SignUpService signUpService;
//    @MockBean()
//    SignInService signInService;
//
//    @InjectMocks
//    private AuthenticationController authenticationController;
//
//
//
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
//    }
//
//
//
//
//    @Test
//    public void signUp() throws Exception {
//
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
//        String message = "This is a secret message";
//
//        doNothing().when(signUpService).signUp(any(UserDto.class));
//        mockMvc.perform(MockMvcRequestBuilders.get("/sign-up")
//                        .contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isOk());
////                        .andExpect(content().string(message)).andReturn();
//
//    }
//
//
//
//    @Test
//    public void testSignUpFailure() throws Exception {
//        UserDto signUpDto = mock(UserDto.class);
//        signUpDto.setEmail("user");
//        signUpDto.setPassword("password");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String signUpDtoJson = objectMapper.writeValueAsString(signUpDto);
//
//        doThrow(new DuplicateUserException("Duplicate user")).when(signUpService).signUp(any(UserDto.class));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/sign-up")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(signUpDtoJson))
//                .andExpect(status().isInternalServerError());
//    }
//
//
//}
