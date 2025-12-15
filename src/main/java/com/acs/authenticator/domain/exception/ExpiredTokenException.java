package com.acs.authenticator.domain.exception;

public class ExpiredTokenException extends RuntimeException {

  public ExpiredTokenException(String message) {
    super(message);
  }
}
