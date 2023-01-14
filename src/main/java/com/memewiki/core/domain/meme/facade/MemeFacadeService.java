package com.memewiki.core.domain.meme.facade;

import com.memewiki.core.domain.meme.domain.Meme;
import com.memewiki.core.domain.meme.repository.MemeRepository;
import com.memewiki.core.domain.meme.request.MemePostRequest;
import com.memewiki.core.domain.memeTag.domain.MemeTag;
import com.memewiki.core.domain.memeTag.repository.MemeTagRepository;
import com.memewiki.core.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemeFacadeService {
    private final MemeRepository memeRepository;
    private final MemeTagRepository memeTagRepository;
    private final TagRepository tagRepository;

    @Transactional
    public Meme saveMemes(MemePostRequest memePostRequest) {
        Meme meme = new Meme(memePostRequest.getImageUrl(), 0, 0);
        memeRepository.save(meme);

        memePostRequest.getTagIds().forEach(
                index -> memeTagRepository.save(new MemeTag(meme, tagRepository.findById(index).get()))
        );

        return meme;

    }
}
