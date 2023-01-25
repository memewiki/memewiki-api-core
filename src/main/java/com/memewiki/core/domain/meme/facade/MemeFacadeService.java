package com.memewiki.core.domain.meme.facade;

import com.memewiki.core.common.response.errorHandler.exception.NoMemeException;
import com.memewiki.core.common.response.errorHandler.exception.NoTagException;
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
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemeFacadeService {
    private final MemeRepository memeRepository;
    private final MemeTagRepository memeTagRepository;
    private final TagRepository tagRepository;

    @Transactional
    public MemeDetailResponse saveMemes(MemeSaveRequest memeSaveRequest) {
        Meme save = memeRepository.save(Meme.builder()
                        .memeUrl(memeSaveRequest.getImageUrl())
                        .memeDownload(0)
                        .memeHit(0)
                        .build());
        List<Tag> tags = new ArrayList<>();

        memeSaveRequest.getTagIds().forEach(
                tagIds -> tags.add(tagRepository.findById(tagIds).orElseThrow(NoTagException::new))
        );

        List<MemeTag> memeTags = new ArrayList<>();
        tags.forEach(
                tag -> memeTags.add(MemeTag.builder()
                                .meme(save)
                                .tag(tag)
                                .build()));
        memeTagRepository.saveAll(memeTags);
        memeTagRepository.save(memeTags.get(0));

        return new MemeDetailResponse(save, tags);
    }

    public MemeDetailResponse findMemesAndHit(Long memeId){
        Meme meme = memeRepository.findById(memeId).orElseThrow(NoMemeException::new);
        meme.hitsUp();
        List<Tag> tags = new ArrayList<>();

        memeTagRepository.findAllByMemeId(memeId).forEach(
                tag -> tags.add(tagRepository.findById(tag.getId()).orElseThrow(NoTagException::new))
        );
        return new MemeDetailResponse(meme, tags);
    }
}
