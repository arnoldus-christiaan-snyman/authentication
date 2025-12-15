package com.acs.authenticator.domain.exception;

public class UserLockedException extends RuntimeException {

  public UserLockedException(String message) {
    super(message);
  }
}
