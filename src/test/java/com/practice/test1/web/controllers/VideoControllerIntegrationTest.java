package com.practice.test1.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.test1.entities.Category;
import com.practice.test1.entities.Video;
import com.practice.test1.repositories.CategoryRepository;
import com.practice.test1.repositories.VideoRepository;
import com.practice.test1.services.VideoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VideoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void givenVideo_whenPerformSaveVideo_thenSaveVideoIsOk() throws Exception {
        //given
        Video v = new Video();
        v.setName("Stref");

        ObjectMapper mapper = new ObjectMapper();
        String reqJson = mapper.writeValueAsString(v);

        //when
        mockMvc.perform(post("/api/videos")
                        .contentType("application/json")
                        .content(reqJson))
                .andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Stref"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        List<Video> videos = videoRepository.findAll();
        Video vid = videos.get(videos.size() - 1);

        assertNotNull(vid);
        assertEquals("Stref", vid.getName());
    }

    @Test
    void givenNullVideo_whenPerformSaveVideo_thenSaveVideoIsBadRequest() throws Exception {
        //given
        Video v = null;

        ObjectMapper mapper = new ObjectMapper();
        String reqJson = mapper.writeValueAsString(v);

        //when
        //then
        mockMvc.perform(post("/api/videos")
                        .contentType("application/json")
                        .content(reqJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void givenVideosInRepository_whenPerformGetAllVideos_thenReturnOk() throws Exception {
        //given
        Video v1 = new Video();
        v1.setName("v1");
        Video v2 = new Video();
        v2.setName("v2");
        Video v3 = new Video();
        v3.setName("v3");

        videoRepository.save(v1);
        videoRepository.save(v2);
        videoRepository.save(v3);

        //when
        //then
        mockMvc.perform(get("/api/videos").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void givenVideoAndCategory_whenPerformAddCategoryToVideo_thenReturnOk() throws Exception {
        //given
        Video v1 = new Video();
        v1.setName("v1");
        Category c = new Category();
        c.setName("c1");

        videoRepository.save(v1);
        categoryRepository.save(c);

        //when
        mockMvc.perform(put("/api/videos/{videoId}/categories/{categoryId}", v1.getId(), c.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        Video v = videoRepository.getById(1L);

        assertNotNull(v.getCategories());
        assertFalse(v.getCategories().isEmpty());
        assertEquals(1, v.getCategories().size());
        assertTrue(v.getCategories().contains(c));
    }

    @Test
    void givenVideoAndNoCategory_whenPerformAddCategoryToVideo_thenReturnBadRequest() throws Exception {
        //given
        Video v1 = new Video();
        v1.setName("v1");

        Category c = new Category();
        c.setName("c1");

        videoRepository.save(v1);

        //when
        mockMvc.perform(put("/api/videos/{videoId}/categories/1", v1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoSuchElementException))
                .andExpect(result -> assertEquals("Category not found: 1", result.getResolvedException().getMessage()));


        //then
        Video v = videoRepository.getById(v1.getId());

        assertNull(v.getCategories());
        assertTrue(v.getCategories().isEmpty());
    }
}