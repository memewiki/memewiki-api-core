package com.memewiki.core.domain.meme.controller;

import com.memewiki.core.common.response.BaseResponse;
import com.memewiki.core.domain.meme.facade.MemeFacadeService;
import com.memewiki.core.domain.meme.request.MemeSaveRequest;
import com.memewiki.core.domain.meme.service.MemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "meme 관련 APIs - v1")
@RequestMapping("/api/v1/memes")
public class MemeController {
    private final MemeService memeService;
    private final MemeFacadeService memeFacadeService;

    @GetMapping("/popular")
    @ApiOperation(value = "인기있는 밈 출력 API")
    public ResponseEntity<BaseResponse> findPopularMemes(){
        BaseResponse baseResponse = BaseResponse.of(HttpStatus.OK, "");
        baseResponse.setData(memeService.findPopularMemes());
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/main/{pagingNum}")
    @ApiOperation(value = "최근 등록 밈 출력 API")
    public ResponseEntity<BaseResponse> findMemesWithPageable(@PathVariable(name = "pagingNum", required = false) Integer pagingNum){
        BaseResponse baseResponse = BaseResponse.of(HttpStatus.OK, "");
        baseResponse.setData(memeService.findMemesWithPageable(pagingNum));
        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping
    @ApiOperation(value = "밈 등록하기")
    public ResponseEntity<BaseResponse> saveMemes(@RequestBody MemeSaveRequest memeSaveRequest) {
        BaseResponse baseResponse = BaseResponse.of(HttpStatus.OK, "");
        baseResponse.setData(memeFacadeService.saveMemes(memeSaveRequest));

        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/{memeId}")
    @ApiOperation(value = "밈 상세보기")
    public ResponseEntity<BaseResponse> findMeme(@PathVariable(name = "memeId", required = true) Long memeId){
        BaseResponse baseResponse = BaseResponse.of(HttpStatus.OK, "");
        return ResponseEntity.ok(baseResponse);
    }

}
