package com.acs.authenticator.domain.model.valueobject;

import com.acs.authenticator.domain.exception.InvalidValueException;

public record Username(String value) {
  public Username {
    if (value == null || value.isBlank()) {
      throw new InvalidValueException("Username cannot be null or blank");
    }
    if (value.length() < 3 || value.length() > 50) {
      throw new InvalidValueException("Username must be between 3 and 50 characters");
    }
    if (value.matches(".*\\s.*")) {
      throw new InvalidValueException("Username must not contain whitespace");
    }
    if (!value.matches("^[a-zA-Z0-9._-]{3,50}$")) {
      throw new InvalidValueException("Username can only contain alphanumeric characters, dots, underscores, and hyphens");
    }
  }
}
