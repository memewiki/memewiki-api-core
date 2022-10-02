package com.memewiki.core.meme.service;

import com.memewiki.core.meme.repository.MemeRepository;
import com.memewiki.core.meme.response.PopularMemeResponse;
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
