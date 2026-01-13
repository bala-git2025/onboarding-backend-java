package com.accenture.onboarding.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.accenture.onboarding.model.LoginResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(OnboardingException.class)
    public ResponseEntity<LoginResponse> handleBusinessException(
            OnboardingException ex) {

        log.warn("Business exception: {}", ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new LoginResponse(
                ex.getMessage(),
                null,
                false
            ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<LoginResponse> handleGenericException(
            Exception ex) {

        log.error("Unhandled exception occurred", ex);

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new LoginResponse(
                "Something went wrong. Please try again later.",
                null,
                false
            ));
    }
}

