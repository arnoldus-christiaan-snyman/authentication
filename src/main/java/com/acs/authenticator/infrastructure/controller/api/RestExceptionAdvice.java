package com.acs.authenticator.infrastructure.controller.api;

import com.acs.authenticator.domain.exception.ExpiredTokenException;
import com.acs.authenticator.domain.exception.IncorrectCredentialsException;
import com.acs.authenticator.domain.exception.InvalidTokenException;
import com.acs.authenticator.domain.exception.InvalidValueException;

import com.acs.authenticator.domain.exception.UserLockedException;
import com.acs.authenticator.domain.exception.UserNotFoundException;
import com.acs.authenticator.infrastructure.controller.model.ApiError;

import com.acs.authenticator.infrastructure.controller.model.ApiResponse;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice(annotations = {RestController.class})
@Slf4j
public class RestExceptionAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({InvalidValueException.class, InvalidTokenException.class})
  public ApiResponse handleInvalidException(RuntimeException ex, WebRequest request) {
    log.error("Handling invalid exception: {}", ex.getMessage());
    return createApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({UserNotFoundException.class})
  public ApiResponse handleNotFoundException(RuntimeException ex, WebRequest request) {
    log.error("Handling not found exception: {}", ex.getMessage());
    return createApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler({ExpiredTokenException.class, IncorrectCredentialsException.class})
  public ApiResponse handleIncorrectException(RuntimeException ex, WebRequest request) {
    log.error("Handling incorrect exception: {}", ex.getMessage());
    return createApiError(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
  }

  @ResponseStatus(HttpStatus.LOCKED)
  @ExceptionHandler({UserLockedException.class})
  public ApiResponse handleLockedException(RuntimeException ex, WebRequest request) {
    log.error("Handling locked exception: {}", ex.getMessage());
    return createApiError(HttpStatus.LOCKED, ex.getMessage(), request);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({Exception.class})
  public ApiResponse handleException(Exception ex, WebRequest request) {
    log.error("Handling exception: {}", ex.getMessage());
    return createApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
  }

  private ApiResponse createApiError(HttpStatus status, String message, WebRequest request) {
    return new ApiResponse(new ApiError(status, message, request.getContextPath(), LocalDateTime.now()));
  }

}
