package com.memewiki.core.domain.meme.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
public class MemePostRequest {
    private String imageUrl;

    @Size(min = 1, max = 3)
    private List<Long> tagIds = new ArrayList<>();
}
