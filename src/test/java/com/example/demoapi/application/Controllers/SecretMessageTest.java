//package com.example.demoapi.application.Controllers;
//
//import com.example.demoapi.application.Services.SecretMessageService;
//import com.example.demoapi.domain.Dto.UserDto;
//import com.example.demoapi.domain.Entities.User;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(SecretMessage.class)
//@EnableWebMvc
//public class SecretMessageTest {
//
////    @MockBean
//    @MockBean()
//    private SecretMessageService secretMessageService;
//    @MockBean
//    private User user;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Before("")
//    public void setUp(){
//        String message = "This is a secret message";
//        when(secretMessageService.getMessage(any()))
//                .thenReturn(message);
//    }
//
//    @Test
//    public void createMockMvc(){
//        assertNotNull(mockMvc);
//    }
//
//    @Test
//    @WithMockUser(username = "test@email")
//    public void testGetSecreteMessage() throws Exception {
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
//        String message = "This is a secret message";
//
//        Mockito.when(secretMessageService.getMessage(Mockito.any(Authentication.class))).thenReturn(message);
//         mockMvc.perform(MockMvcRequestBuilders.get("/secret-message")
//                        .contentType(MediaType.TEXT_PLAIN_VALUE)
//                        .header("Authorization", "Bearer " + token))
//                        .andExpect(status().isOk());
////                        .andExpect(content().string(message)).andReturn();
//
//
//    }
//
//    @Test
//    @WithMockUser(username = "test@email")
//    public void shouldReturnUnAuthorised() throws Exception {
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.get("/secret-message"))
//                .andExpect(status().isUnauthorized());
//    }
//
//}
