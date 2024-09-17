package com.mari.reservemystay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.ResourceBundle;

import static com.mari.reservemystay.exception.BusinessException.BASE_BUNDLE_PATH;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {

        String string = ResourceBundle.getBundle(BASE_BUNDLE_PATH).getString(ex.getKey());
        ErrorDetail errorDetail = new ErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR.value(), string);
        ErrorResponse errorResponse = new ErrorResponse(Collections.singletonList(errorDetail));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorDetail errorDetail = new ErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(Collections.singletonList(errorDetail));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
