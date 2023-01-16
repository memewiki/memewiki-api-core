package com.memewiki.core.domain.meme.repository;

import com.memewiki.core.domain.meme.response.MemeRecentResponse;
import com.memewiki.core.domain.meme.response.MemePopularResponse;

import java.util.List;

public interface MemeRepositoryCustom {
    List<MemePopularResponse> findPopularMemes();

    List<MemeRecentResponse> findMemesWithPageable(Integer pagingNum);
}
