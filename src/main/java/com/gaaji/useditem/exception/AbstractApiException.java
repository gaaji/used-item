package com.gaaji.useditem.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AbstractApiException extends RuntimeException implements ErrorCode {

	private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorName;
    private final String errorMessage;

    public AbstractApiException (ErrorCode errorCode) {

        httpStatus = errorCode.getHttpStatus();
        this.errorCode = errorCode.getErrorCode();
        errorName = errorCode.getErrorName();
        errorMessage = errorCode.getErrorMessage();
    }

}