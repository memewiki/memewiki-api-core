package com.memewiki.core.domain.meme.response;

import com.memewiki.core.domain.meme.domain.Meme;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemePopularResponse {
    private Long memeId;
    private String memeUrl;
    private Integer memeHit;
    private Integer memeDownload;
    private LocalDateTime createdAt;

    @QueryProjection
    public MemePopularResponse(Meme meme){
        this.memeId = meme.getId();
        this.memeUrl = meme.getMemeUrl();
        this.memeHit = meme.getMemeHit();
        this.memeDownload = meme.getMemeDownload();
        this.createdAt = meme.getCreatedAt();
    }
}
