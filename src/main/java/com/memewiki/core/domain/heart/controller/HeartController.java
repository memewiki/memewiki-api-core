package com.memewiki.core.domain.heart.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "좋아요 API - v1")
@RequestMapping("/api/v1/heart")
public class HeartController {

}
