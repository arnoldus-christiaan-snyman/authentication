package com.acs.authenticator.domain.exception;

public class ExistingUserException extends RuntimeException {

  public ExistingUserException(String message) {
    super(message);
  }
}
