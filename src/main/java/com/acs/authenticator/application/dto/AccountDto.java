package com.acs.authenticator.application.dto;

import com.acs.authenticator.domain.model.Role;
import java.util.Locale;
import java.util.UUID;

public record AccountDto(UUID id, String name, String surname, String email, Role role, Locale localization, boolean isActive) {

  public AccountDto(String name, String surname, String email, Role role, Locale localization) {
    this(null, name, surname, email, role, localization, true);
  }
}
