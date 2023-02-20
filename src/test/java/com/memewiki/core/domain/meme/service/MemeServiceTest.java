package com.memewiki.core.domain.meme.service;

import com.memewiki.core.domain.meme.facade.MemeFacadeService;
import com.memewiki.core.domain.meme.request.MemeSaveRequest;
import com.memewiki.core.domain.meme.response.MemeDetailResponse;
import com.memewiki.core.domain.meme.response.MemeRecentResponse;
import com.memewiki.core.domain.tag.domain.Tag;
import com.memewiki.core.domain.tag.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class MemeServiceTest {
    @Autowired MemeService memeService;

    @Autowired
    private EntityManager em;
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private MemeFacadeService memeFacadeService;

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
    void 밈리스트확인() {
        // given

        // Tag Datas
        List<Long> lists = new ArrayList<>();
        lists.add(1L);
        lists.add(2L);
        lists.add(4L);

        // expected datas
        List<MemeRecentResponse> expect = new ArrayList<>();

        for(int i=0; i<50; i++){
            MemeSaveRequest memeSaveRequest = new MemeSaveRequest("url-test" + i, lists);
            MemeDetailResponse memeDetailResponse = memeFacadeService.saveMemes(memeSaveRequest);
            expect.add(new MemeRecentResponse(memeDetailResponse));     // Test data 입력
        }

        // when
        List<MemeRecentResponse> memesWithPageable = memeService.findMemesWithPageable(0L);

        // then
        for(int i=0; i<memesWithPageable.size(); i++){
            System.out.println(memesWithPageable.get(i).getMemeId() + " <-> " + expect.get(i).getMemeId());
            System.out.println(memesWithPageable.get(i).getMemeUrl() + " <-> " + expect.get(i).getMemeUrl());
            System.out.println(memesWithPageable.get(i).getTagMemeRecentResponses().get(0).getTagName() + " <-> " + expect.get(i).getTagMemeRecentResponses().get(0).getTagName());
            System.out.println(memesWithPageable.get(i).getTagMemeRecentResponses().get(1).getTagName() + " <-> " + expect.get(i).getTagMemeRecentResponses().get(1).getTagName());
            System.out.println(memesWithPageable.get(i).getTagMemeRecentResponses().get(2).getTagName() + " <-> " + expect.get(i).getTagMemeRecentResponses().get(2).getTagName());
        }
    }
}