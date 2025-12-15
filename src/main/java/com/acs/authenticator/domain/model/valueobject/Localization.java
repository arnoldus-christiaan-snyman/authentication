package com.acs.authenticator.domain.model.valueobject;

import com.acs.authenticator.domain.exception.InvalidValueException;
import java.util.Locale;
import java.util.Objects;

public record Localization(Locale local) {

  public Localization(String language, String country) {
    this(Locale.of(language, country));
  }

  public Localization {
    if(Objects.isNull(local)) {
      throw new InvalidValueException("Localization information is not correct");
    }
  }
}
