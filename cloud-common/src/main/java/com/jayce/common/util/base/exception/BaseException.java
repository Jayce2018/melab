package com.jayce.common.util.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class BaseException extends RuntimeException {
    private int status = HttpStatus.OK.value();

    public BaseException() {
    }

    public BaseException(ExceptionCodeUtil.ExceptionTypeEnum exceptionTypeEnum, String message, HttpStatus httpStatus) {
        //super(ExceptionCodeUtil.bulidExceptionCodeAndMessage(exceptionTypeEnum, message));
        super(message);
        this.status = httpStatus.value();
    }

    public BaseException(ExceptionCodeUtil.ExceptionTypeEnum exceptionTypeEnum, String message, int status) {
        //super(ExceptionCodeUtil.bulidExceptionCodeAndMessage(exceptionTypeEnum, message));
        super(message);
        this.status = status;
    }

    public BaseException(ExceptionCodeUtil.ExceptionTypeEnum exceptionTypeEnum, String message) {
        //super(ExceptionCodeUtil.bulidExceptionCodeAndMessage(exceptionTypeEnum, message));
        super(message);
        this.status = HttpStatus.BAD_REQUEST.value();
    }

    public BaseException(ExceptionCodeUtil.ExceptionTypeEnum exceptionTypeEnum, String message, Throwable cause) {
        //super(ExceptionCodeUtil.bulidExceptionCodeAndMessage(exceptionTypeEnum, message), cause);
        super(message, cause);
        this.status = HttpStatus.BAD_REQUEST.value();
    }

    public BaseException(Throwable cause) {
        super(cause);
        this.status = HttpStatus.BAD_REQUEST.value();
    }

    public BaseException(ExceptionCodeUtil.ExceptionTypeEnum exceptionTypeEnum, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        //super(ExceptionCodeUtil.bulidExceptionCodeAndMessage(exceptionTypeEnum, message), cause, enableSuppression, writableStackTrace);
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = HttpStatus.BAD_REQUEST.value();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
