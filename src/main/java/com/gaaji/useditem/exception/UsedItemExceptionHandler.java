package com.gaaji.useditem.exception;

import static com.gaaji.useditem.exception.UsedItemErrorCode.INDEX_OUT_OF_BOUNDS;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UsedItemExceptionHandler {

    @ExceptionHandler(AbstractApiException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(AbstractApiException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(ex, request.getRequestURI()));
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<ErrorResponse> handleIndexOutOfBoundsException(
            HttpServletRequest request) {
        return ResponseEntity.status(INDEX_OUT_OF_BOUNDS.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(INDEX_OUT_OF_BOUNDS,
                        request.getRequestURI()));
    }
}
