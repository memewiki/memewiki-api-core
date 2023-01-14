package com.memewiki.core.domain.meme.repository;

import com.memewiki.core.domain.meme.response.MemeResponse;
import com.memewiki.core.domain.meme.response.PopularMemeResponse;

import java.util.List;

public interface MemeRepositoryCustom {
    List<PopularMemeResponse> findPopularMemes();

    List<MemeResponse> findMemesWithPageable(Integer pagingNum);
}
