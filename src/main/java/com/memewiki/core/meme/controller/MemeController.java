package com.memewiki.core.meme.controller;

import com.memewiki.core.meme.response.PopularMemeResponse;
import com.memewiki.core.meme.service.MemeService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/memes")
@RequiredArgsConstructor
@Api(tags = "meme 관련 APIs - v1")
public class MemeController {
    private final MemeService memeService;

    @GetMapping("/popular")
    public List<PopularMemeResponse> findPopularMemes(){
        return memeService.findPopularMemes();
    }
}
