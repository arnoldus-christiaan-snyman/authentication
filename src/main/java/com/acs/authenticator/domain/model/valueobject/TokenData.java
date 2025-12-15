package com.acs.authenticator.domain.model.valueobject;

import com.acs.authenticator.domain.exception.InvalidValueException;
import java.util.Date;
import java.util.Objects;

public record TokenData(String accessToken, Date accessTokenExpiryDate, String refreshToken, Date refreshTokenExpiryDate, Date createdAt) {

  public TokenData(String accessToken, Date accessTokenExpiryDate, Date createdAt) {
    this(accessToken, accessTokenExpiryDate, null, null, createdAt);
  }

  public TokenData {
    if(Objects.isNull(accessToken) || accessToken.isBlank()) {
      throw new InvalidValueException("Access token is required");
    }
    if(Objects.isNull(accessTokenExpiryDate)) {
      throw new InvalidValueException("Access token expiry date is required");
    }
    if(Objects.isNull(createdAt)) {
      throw new InvalidValueException("Creation date date is required");
    }
    if((Objects.nonNull(refreshToken) && !refreshToken.isBlank()) && Objects.isNull(refreshTokenExpiryDate)) {
      throw new InvalidValueException("Refresh token is provided without an expire date");
    }
  }
}
