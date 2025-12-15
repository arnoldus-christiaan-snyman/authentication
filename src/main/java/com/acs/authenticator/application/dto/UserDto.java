package com.acs.authenticator.application.dto;

import java.util.UUID;

public record UserDto(UUID id, UUID accountId, String username, String password, boolean isLocked, boolean isActive) {

  public UserDto(UUID accountId, String username, String password) {
    this(null, accountId, username, password, false, true);
  }
}
