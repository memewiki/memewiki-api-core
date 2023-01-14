package com.memewiki.core.domain.meme.controller;

import com.memewiki.core.domain.meme.request.MemePostRequest;
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
import java.util.ArrayList;
import java.util.List;
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


    @BeforeEach
    public void tagDataIn() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("화남"));
        tags.add(new Tag("슬픔"));
        tags.add(new Tag("분노"));
        tags.add(new Tag("퇴근"));
        tags.add(new Tag("출근"));
        tagRepository.saveAll(tags);
    }

    @Test
    void 밈저장테스트() throws Exception {
        // given
        List<Long> lists = new ArrayList<>();
        lists.add(1L);
        lists.add(2L);
        lists.add(4L);
        MemePostRequest memePostRequest = new MemePostRequest("url-test", lists);

        // when
        ResultActions perform = mvc.perform(post("/api/v1/memes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memePostRequest))
                .accept(MediaType.APPLICATION_JSON)
        );

        // then
        perform.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findMemesWithPageable() {
    }
}