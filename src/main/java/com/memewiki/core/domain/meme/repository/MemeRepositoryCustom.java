package com.memewiki.core.domain.meme.repository;

import com.memewiki.core.domain.meme.response.MemePopularResponse;
import com.memewiki.core.domain.meme.response.MemeRecentResponse;

import java.util.List;

public interface MemeRepositoryCustom {
    List<MemePopularResponse> findPopularMemes();

    List<MemeRecentResponse> findMemesWithPageable(Long pagingNum);
}
