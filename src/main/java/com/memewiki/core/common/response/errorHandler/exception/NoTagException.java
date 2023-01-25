package com.memewiki.core.common.response.errorHandler.exception;

public class NoTagException extends BusinessException{
    public NoTagException() {
        super(ExceptionCode.TAG_EXIST_ERROR);
    }
}