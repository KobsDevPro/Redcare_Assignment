package com.tech.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.assignment.pojo.Item;
import com.tech.assignment.pojo.Root;
import com.tech.assignment.service.RepoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureJsonTesters
@WebMvcTest(RepoController.class)
class RepoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RepoService repoService;

    private Root result;

    @Autowired
    private JacksonTester<List<Item>> paJacksonTester;

    @BeforeEach
    void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(new File("src/test/resources/test.json"), Root.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldReturnOkStatusForSortByStar() throws Exception {
        //given
        Mockito.when(repoService.sortByStars()).thenReturn(result.items);
        // when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/sortByStar")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(
                paJacksonTester.write(result.items)
                        .getJson());
    }

    @Test
    public void shouldReturnOkStatusForSortByTopNumber() throws Exception {
        //given
        Mockito.when(repoService.sortByTopNumber(1)).thenReturn(result.items);
        // when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/sortByTopNumber?number=1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(
                paJacksonTester.write(result.items)
                        .getJson());
    }

    @Test
    public void shouldReturnOkStatusForFilterByLanguage() throws Exception {
        //given
        Mockito.when(repoService.filterByLanguage("html")).thenReturn(result.items);
        // when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/filterByLanguage?language=html")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(
                paJacksonTester.write(result.items)
                        .getJson());
    }

    @Test
    public void shouldReturnOkStatusForFilterByDate() throws Exception {
        //given
        Mockito.when(repoService.filterByDate("2016-06-28")).thenReturn(result.items);
        // when
        MockHttpServletResponse response = mockMvc.perform(
                        get("/filterByDate?date=2016-06-28")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(
                paJacksonTester.write(result.items)
                        .getJson());
    }
}