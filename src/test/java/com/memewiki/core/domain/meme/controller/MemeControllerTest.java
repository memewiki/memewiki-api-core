package com.memewiki.core.domain.meme.controller;

import com.memewiki.core.common.response.errorHandler.exception.NoMemeException;
import com.memewiki.core.domain.meme.request.MemeSaveRequest;
import com.memewiki.core.domain.tag.domain.Tag;
import com.memewiki.core.domain.tag.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import javax.management.ServiceNotFoundException;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MemeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private EntityManager em;


    @BeforeEach
    public void tagDataIn() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.builder().tagName("화남").build());
        tags.add(Tag.builder().tagName("슬픔").build());
        tags.add(Tag.builder().tagName("분노").build());
        tags.add(Tag.builder().tagName("퇴근").build());
        tags.add(Tag.builder().tagName("출근").build());
        tags.add(Tag.builder().tagName("퇴사").build());
        tagRepository.saveAll(tags);
    }

    @Test
    void 밈저장테스트() throws Exception {
        // given
        List<Long> lists = new ArrayList<>();
        lists.add(1L);
        lists.add(2L);
        lists.add(4L);
        MemeSaveRequest memeSaveRequest = new MemeSaveRequest("url-test", lists);

        // when
        ResultActions perform = mvc.perform(post("/api/v1/memes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memeSaveRequest))
                .accept(MediaType.APPLICATION_JSON)
        );

        // then
        perform.andExpect(status().isOk())
                .andDo(print());

        em.flush();

        ResultActions perform2 = mvc.perform(get("/api/v1/memes/1"));

        perform2.andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void 에러테스트_밈없음() throws Exception {
        // given
        List<Long> lists = new ArrayList<>();
        lists.add(1L);
        lists.add(2L);
        lists.add(4L);
        MemeSaveRequest memeSaveRequest = new MemeSaveRequest("url-test", lists);

        // when
        ResultActions perform = mvc.perform(post("/api/v1/memes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memeSaveRequest))
                .accept(MediaType.APPLICATION_JSON)
        );

        // then
        perform.andExpect(status().isOk())
                .andDo(print());

        em.flush();

        mvc.perform(get("/api/v1/memes/2"))
                .andExpect((rslt) -> assertTrue(rslt.getResolvedException().getClass().isAssignableFrom(NoMemeException.class)));

    }

    @Test
    void 에러테스트_태그없음() throws Exception {
        // given
        List<Long> lists = new ArrayList<>();
        lists.add(1L);
        lists.add(2L);
        lists.add(4111L);
        MemeSaveRequest memeSaveRequest = new MemeSaveRequest("url-test", lists);

        // when
        ResultActions perform = mvc.perform(post("/api/v1/memes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memeSaveRequest))
                .accept(MediaType.APPLICATION_JSON)
        );

        // then
        perform.andExpect(status().isOk())
                .andDo(print());

        em.flush();

        mvc.perform(get("/api/v1/memes/2"))
                .andExpect((rslt) -> assertTrue(rslt.getResolvedException().getClass().isAssignableFrom(NoMemeException.class)));

    }

}