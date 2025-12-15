package com.acs.authenticator.application.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public record TokenDto(String accessToken, LocalDateTime accessTokenExpire, String refreshToken, LocalDateTime refreshTokenExpire, LocalDateTime issuedAt) {

  public TokenDto(String accessToken, LocalDateTime accessTokenExpire, LocalDateTime issuedAt) {
    this(accessToken, accessTokenExpire, null, null, issuedAt);
  }

  public TokenDto {
    if(Objects.isNull(accessToken) || accessToken.isBlank()) {
      throw new IllegalArgumentException("Access token is required");
    }
    if(Objects.isNull(accessTokenExpire)) {
      throw new IllegalArgumentException("Access token expiry date is required");
    }
  }
}
