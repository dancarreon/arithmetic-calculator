package com.calculator.controller;

import com.calculator.model.User;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(username = "user1@test.com")
public class UserControllerTest extends GenericControllerTest {

    @Test
    public void shouldGetMe() throws Exception {
        mockMvc.perform(get("/user/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetUserById() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");

        when(userService.getUserById(1L)).thenReturn(user.toRecord());

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("username")));
    }

    @Test
    public void shouldGetUserBalance() throws Exception {
        mockMvc.perform(get("/user/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    public void shouldGetUserRecords() throws Exception {
        mockMvc.perform(get("/user/records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
