// package com.petr.postcode_api.user;

// import java.util.ArrayList;
// import java.util.List;

// // Core imports
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.context.bean.override.mockito.MockitoBean;

// // Static imports for test DSL
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.mockito.Mockito.when;

// @WebMvcTest(UserController.class)
// public class UserControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockitoBean
//     private UserService userService;

//     // @MockitoBean
//     // private UserRepository repo;

//     private List<User> users;

//     @BeforeEach
//     void setup() {
//         users = new ArrayList<>();
        
//         User u1 = new User();
//         u1.setUsername("petr");
//         u1.setPassword("password1");
        
//         User u2 = new User();
//         u2.setUsername("tom");
//         u2.setPassword("password2");
        
//         users.add(u1);
//         users.add(u2);
//     }

//     @Test
//     void shouldFindAllUsers_returnsUsers() throws Exception {
//         // Mock the service response
//         //given(userService.getAll()).willReturn(users);
//         when(userService.getAll()).thenReturn(users);
        
//         // Perform the request and verify
//         mockMvc.perform(get("/users"))
//             .andExpect(status().isOk())
//             .andExpect(jsonPath("$.flag").exists())
//             .andExpect(jsonPath("$.data").isArray())
//             .andExpect(jsonPath("$.flag").value(true))
//             .andExpect(jsonPath("$.code").value(200))
//             .andExpect(jsonPath("$.message").value("Find All Success"))
//             .andExpect(jsonPath("$.data.length()").value(2))
//             .andExpect(jsonPath("$.data[0].username").value("petr"))
//             .andExpect(jsonPath("$.data[1].username").value("tom"));
//     }
// }