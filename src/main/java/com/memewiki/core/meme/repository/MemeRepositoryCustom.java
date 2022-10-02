package com.memewiki.core.meme.repository;

import com.memewiki.core.meme.response.PopularMemeResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemeRepositoryCustom {
    List<PopularMemeResponse> findPopularMemes();

}
