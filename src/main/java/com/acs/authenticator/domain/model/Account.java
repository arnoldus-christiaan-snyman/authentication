package com.acs.authenticator.domain.model;

import com.acs.authenticator.domain.model.valueobject.Email;
import com.acs.authenticator.domain.model.valueobject.Localization;
import java.util.UUID;

public record Account(UUID id, String name, String surname, Email email, Role role, Localization localization, boolean isActive) {

  public Account(String name, String surname, Email email, Role role, Localization localization) {
    this(null, name, surname, email, role, localization, true);
  }
}
