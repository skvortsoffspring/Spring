package com.example.skvortsoff;

import com.example.skvortsoff.entity.User;
import com.example.skvortsoff.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
public class JUnitControllerUserTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserRepository repository;

    @Test
    public void checkStatusSaveUser() throws Exception {
        User person = new User("Skvortsoff", "skvortsoff@mail.com", "29-123-45-67", "password");
        Mockito.when(repository.save(Mockito.any())).thenReturn(person);

        mockMvc.perform(
                        post("/api/auth/register")
                                .content(objectMapper.writeValueAsString(person))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    @Test
    public void CheckUserDataCorrectToSave() throws Exception {
        User user = new User("Skvortsoff", "skvortsoff@mail.com", "29-123-45-670", "password");
        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(Optional.of(user));

        mockMvc.perform(
                        get("/api/auth/get-user/{email}", user.getEmail()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("Skvortsoff"))
                .andExpect(jsonPath("$.email").value("skvortsoff@mail.com"));
    }

    @Test
    public void CheckUserFoundExceptions() throws Exception {
        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(Optional.empty());

        mockMvc.perform(
                        get("/api/auth/get-exist/{email}", "skvortsoff@mail.com"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void TryingDeleteUser() throws Exception {
        User user = new User(1L, "skvortsoff@mail.com");

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(user));
        mockMvc.perform(
                        delete("/api/auth/delete/{id}",user.getId()))
                .andExpect(status().isOk());
    }

}
