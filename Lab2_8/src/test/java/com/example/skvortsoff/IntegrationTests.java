package com.example.skvortsoff;

import com.example.skvortsoff.dto.CategoryDtoNew;
import com.example.skvortsoff.dto.CategoryIdDto;
import com.example.skvortsoff.repository.CategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
public class IntegrationTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private CategoryRepository categoryRepository;
    private final String nameCategory = "New Category";

    public MockHttpServletRequestBuilder postJson(String uri, Object body) {
        try {
            String json = new ObjectMapper().writeValueAsString(body);
            return  post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public MockHttpServletRequestBuilder deleteJson(String uri, Object body) {
        try {
            String json = new ObjectMapper().writeValueAsString(body);
            return  delete(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void aGetCategories() throws Exception {
        this.mvc.perform(get("/api/categories/names"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void bCreateCategory() throws Exception {
        CategoryDtoNew categoryDtoNew = new CategoryDtoNew(nameCategory, null);

        this.mvc.perform(postJson("/api/categories/admin/add", categoryDtoNew))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void cDeleteCategory() throws Exception {
        Long id = categoryRepository.findByName(nameCategory).getId();

        CategoryIdDto categoryIdDto = new CategoryIdDto(id);

        this.mvc.perform(deleteJson("/api/categories/admin/del", categoryIdDto))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
