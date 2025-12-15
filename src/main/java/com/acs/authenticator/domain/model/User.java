package com.acs.authenticator.domain.model;

import com.acs.authenticator.domain.model.valueobject.Password;
import com.acs.authenticator.domain.model.valueobject.Username;
import java.util.UUID;

public record User(UUID id, UUID accountId, Username username, Password password, boolean isLocked, boolean isActive) {

  public User(UUID accountId, Username username, Password password) {
    this(null, accountId, username, password, false, true);
  }
}
