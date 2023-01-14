package com.memewiki.core.domain.meme.response;

import com.memewiki.core.domain.meme.domain.Meme;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemeResponse {
    private Long memeId;
    private String memeUrl;
    private Integer memeHit;
    private LocalDateTime createdAt;

    @QueryProjection
    public MemeResponse(Meme meme){
        memeId = meme.getId();
        memeUrl = meme.getMemeUrl();
        memeHit = meme.getMemeHit();
        createdAt = meme.getCreatedAt();
    }
}
