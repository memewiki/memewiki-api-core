package com.memewiki.core.common.response.errorHandler.exception;

public class NoMemeException extends BusinessException{
    public NoMemeException() {
        super(ExceptionCode.MEME_EXIST_ERROR);
    }
}
