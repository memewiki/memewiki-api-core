package com.memewiki.core.domain.tag.response;

import com.memewiki.core.domain.tag.domain.Tag;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class TagMemeRecentResponse {
    private Long id;
    private String tagName;

    public TagMemeRecentResponse(Tag tag) {
        this.id = tag.getId();
        this.tagName = tag.getTagName();
    }

}
