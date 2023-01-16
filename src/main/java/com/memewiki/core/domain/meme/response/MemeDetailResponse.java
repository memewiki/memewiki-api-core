package com.memewiki.core.domain.meme.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.memewiki.core.domain.meme.domain.Meme;
import com.memewiki.core.domain.tag.domain.Tag;
import com.memewiki.core.domain.tag.response.TagMemeDetailResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class MemeDetailResponse {
    private Long memeId;
    private String memeUrl;
    private LocalDateTime createdAt;
    private Integer memeHit;
    private final List<TagMemeDetailResponse> tagMemeDetailResponses = new ArrayList<>();

    public MemeDetailResponse(Meme meme, List<Tag> tags) {
        this.memeId = meme.getId();
        this.memeUrl = meme.getMemeUrl();
        this.createdAt = meme.getCreatedAt();
        this.memeHit = meme.getMemeHit();
        tags.forEach(
            index -> this.tagMemeDetailResponses.add(new TagMemeDetailResponse(index))
        );
    }
}
