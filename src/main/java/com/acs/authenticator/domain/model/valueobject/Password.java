package com.acs.authenticator.domain.model.valueobject;

import com.acs.authenticator.domain.exception.InvalidValueException;

public record Password(String value) {
  public Password {
    if (value == null || value.isBlank() || value.length() < 8) {
      throw new InvalidValueException("Password must be at least 8 characters long");
    }
    if (value.matches(".*\\s.*")) {
      throw new InvalidValueException("Password must not contain whitespace");
    }
  }
}
