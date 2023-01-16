package com.memewiki.core.domain.tag.response;

import com.memewiki.core.domain.tag.domain.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TagMemeDetailResponse {
    private Long id;
    private String tagName;

    public TagMemeDetailResponse(Tag tag) {
        this.id = tag.getId();
        this.tagName = tag.getTagName();
    }
}
