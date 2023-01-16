package com.memewiki.core.domain.meme.facade;

import com.memewiki.core.domain.meme.domain.Meme;
import com.memewiki.core.domain.meme.repository.MemeRepository;
import com.memewiki.core.domain.meme.request.MemeSaveRequest;
import com.memewiki.core.domain.meme.response.MemeDetailResponse;
import com.memewiki.core.domain.memeTag.domain.MemeTag;
import com.memewiki.core.domain.memeTag.repository.MemeTagRepository;
import com.memewiki.core.domain.tag.domain.Tag;
import com.memewiki.core.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemeFacadeService {
    private final MemeRepository memeRepository;
    private final MemeTagRepository memeTagRepository;
    private final TagRepository tagRepository;

    @Transactional
    public MemeDetailResponse saveMemes(MemeSaveRequest memeSaveRequest) {
        Meme save = memeRepository.save(new Meme(memeSaveRequest.getImageUrl(), 0, 0));
        List<Tag> tags = new ArrayList<>();

        memeSaveRequest.getTagIds().forEach(
                tagIds -> tags.add(tagRepository.findById(tagIds).get())
        );

        tags.forEach(
                tag -> memeTagRepository.save(new MemeTag(save, tag))
        );

        return new MemeDetailResponse(save, tags);
    }


}
