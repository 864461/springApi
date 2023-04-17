//package com.example.demoapi.application.Controllers;
//
//import com.example.demoapi.application.Services.PasswordResetService;
//import com.example.demoapi.domain.Dto.UserDto;
//import com.example.demoapi.domain.Entities.User;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//@ExtendWith(MockitoExtension.class)
//class UserResetPasswordTest {
//
//    @Mock
//    private PasswordResetService passwordResetService;
//
//    private MockMvc mockMvc;
//
//
////    @BeforeEach
////    void setUp(WebApplicationContext webApplicationContext) {
////        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserResetPassword(passwordResetService)).build();
////    }
//
//    @Test
//    @WithMockUser(username = "test@email")
//    void resetMyPassword() throws Exception {
//        User user = new User();
//        user.setEmail("test@example.com");
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
//        mockMvc = MockMvcBuilders.standaloneSetup(new UserResetPassword(passwordResetService)).build();
//        UserDto newPassword = new UserDto("TEST","TEST","test@test","1234");
//        mockMvc = MockMvcBuilders.standaloneSetup(new UserResetPassword(passwordResetService)).build();
//
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/reset-my-password")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("Authorization", "Bearer " + token)
////                        .content("Json")
////                        .principal((Authentication) () -> user)
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        Mockito.verify(passwordResetService, Mockito.times(1)).resetPassword(Mockito.any(), Mockito.any());
//    }
//
//
//    class MyObject {
//        private String username;
//        private String password;
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//
//        // getters and setters
//    }
//}
