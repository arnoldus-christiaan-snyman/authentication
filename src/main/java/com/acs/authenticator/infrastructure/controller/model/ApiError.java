package com.acs.authenticator.infrastructure.controller.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public final class ApiError {

  private final int status;
  private final String error;
  private final String message;
  private final String path;
  private final long timestamp;

  public ApiError (HttpStatus status, String message, String path, long timestamp) {
    this(status.value(), status.getReasonPhrase(), message, path, timestamp);
  }

  public ApiError (HttpStatus status, String message, String path,
      LocalDateTime timestamp) {
    this(status, message, path, timestamp.toInstant(ZoneOffset.UTC).toEpochMilli());
  }

}
