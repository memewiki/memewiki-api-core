package com.memewiki.core.domain.meme.service;

import com.memewiki.core.domain.meme.response.PopularMemeResponse;
import com.memewiki.core.domain.meme.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemeService {
    private final MemeRepository memeRepository;

    @Transactional(readOnly = true)
    public List<PopularMemeResponse> findPopularMemes(){
        return memeRepository.findPopularMemes();
    }
}
