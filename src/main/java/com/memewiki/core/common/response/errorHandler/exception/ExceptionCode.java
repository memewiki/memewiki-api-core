package com.memewiki.core.common.response.errorHandler.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExceptionCode {

    // Meme
    MEME_EXIST_ERROR(HttpStatus.BAD_REQUEST, "U001", "존재하지 않는 밈입니다."),

    // Tag
    TAG_EXIST_ERROR(HttpStatus.BAD_REQUEST, "U001", "존재하지 않는 태그입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ExceptionCode(final HttpStatus httpStatus, final String code, final String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }
}
