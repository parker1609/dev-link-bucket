package com.codemcd.myarchive.web.support.error;

import com.codemcd.myarchive.domain.exception.NotFoundLinkTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        ErrorResponseDto response = new ErrorResponseDto(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundLinkTypeException.class)
    protected ResponseEntity<ErrorResponseDto> handleMismatchLinkTypeException(NotFoundLinkTypeException e) {
        log.error("handleMismatchLinkTypeException", e);
        ErrorResponseDto response = new ErrorResponseDto(ErrorCode.INVALID_INPUT_VALUE);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponseDto> handleServerException(IllegalArgumentException e) {
        log.error("handleServerException", e);
        ErrorResponseDto response = new ErrorResponseDto(ErrorCode.SERVER_ERROR);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
