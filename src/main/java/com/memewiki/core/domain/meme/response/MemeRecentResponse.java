package com.memewiki.core.domain.meme.response;

import com.memewiki.core.domain.meme.domain.Meme;
import com.memewiki.core.domain.tag.domain.Tag;
import com.memewiki.core.domain.tag.response.TagMemeRecentResponse;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class MemeRecentResponse {
    private Long memeId;
    private String memeUrl;
    private Integer memeHit;
    private LocalDateTime createdAt;

    private final List<TagMemeRecentResponse> tagMemeRecentResponses = new ArrayList<>();

    @QueryProjection
    public MemeRecentResponse(Meme meme, List<Tag> tagMemeRecentResponsesIn){
        this.memeId = meme.getId();
        this.memeUrl = meme.getMemeUrl();
        this.memeHit = meme.getMemeHit();
        this.createdAt = meme.getCreatedAt();
        tagMemeRecentResponsesIn.forEach(
                tag -> this.tagMemeRecentResponses.add(
                        TagMemeRecentResponse.builder()
                                .id(tag.getId())
                                .tagName(tag.getTagName())
                                .build()
                )
        );
    }

    public MemeRecentResponse(MemeDetailResponse memeDetailResponse){
        this.memeId = memeDetailResponse.getMemeId();
        this.memeUrl = memeDetailResponse.getMemeUrl();
        this.memeHit = memeDetailResponse.getMemeHit();
        this.createdAt = memeDetailResponse.getCreatedAt();
        memeDetailResponse.getTagMemeDetailResponses().forEach(
                tag -> this.tagMemeRecentResponses.add(
                        TagMemeRecentResponse.builder()
                                .id(tag.getId())
                                .tagName(tag.getTagName())
                                .build()
                )
        );
    }
}
