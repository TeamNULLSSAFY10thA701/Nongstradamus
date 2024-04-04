package com.a701.nongstradamus.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.a701.nongstradamus.common.CommonDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    // EntityNotFoundException 처리(404)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CommonDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new CommonDto(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    // HttpMethodNotSupportedException 처리
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CommonDto> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(new CommonDto(HttpStatus.NOT_IMPLEMENTED.value(), "지원하지 않는 메소드입니다."));
    }

    // NoResourceFoundException 처리
    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public ResponseEntity<CommonDto> handleNoResourceFoundException(org.springframework.web.servlet.resource.NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new CommonDto(HttpStatus.NOT_IMPLEMENTED.value(), "지원하지 않는 주소입니다."));
    }

}
