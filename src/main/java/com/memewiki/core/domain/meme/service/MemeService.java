package com.memewiki.core.domain.meme.service;
import com.memewiki.core.domain.meme.response.MemePopularResponse;
import com.memewiki.core.domain.meme.response.MemeRecentResponse;
import com.memewiki.core.domain.meme.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemeService {
    private final MemeRepository memeRepository;

    public List<MemePopularResponse> findPopularMemes(){
        return memeRepository.findPopularMemes();
    }

    public List<MemeRecentResponse> findMemesWithPageable(Integer pagingNum){
        return memeRepository.findMemesWithPageable(pagingNum);
    }

}
