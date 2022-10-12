package com.memewiki.core.domain.meme.controller;

import com.memewiki.core.domain.meme.response.PopularMemeResponse;
import com.memewiki.core.domain.meme.service.MemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "meme 관련 APIs - v1")
@RequestMapping("/api/v1/memes")
public class MemeController {
    private final MemeService memeService;

    @GetMapping("/popular")
    @ApiOperation(value = "인기있는 밈 출력 API")
    public List<PopularMemeResponse> findPopularMemes(){
        return memeService.findPopularMemes();
    }
}
