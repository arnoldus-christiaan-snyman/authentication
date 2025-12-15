package com.acs.authenticator.domain.model.valueobject;

import com.acs.authenticator.domain.exception.InvalidValueException;

public record Email(String value) {
  public Email {
    if(value == null || value.isBlank()) {
      throw new InvalidValueException("Email is required");
    }
    if (!value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
      throw new InvalidValueException("Invalid email format");
    }
  }
}
