package com.acs.authenticator.application.mapper;

import com.acs.authenticator.application.dto.TokenDto;
import com.acs.authenticator.domain.model.Token;
import com.acs.authenticator.domain.model.valueobject.TokenData;

import java.time.ZoneId;
import java.util.Date;

public final class TokenMapper {

  private TokenMapper() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static TokenDto toDto(Token token) {
    TokenData data = token.tokenData();
    return new TokenDto(
        data.accessToken(),
        data.accessTokenExpiryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
        data.refreshToken(),
        data.refreshTokenExpiryDate() != null ?
            data.refreshTokenExpiryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null,
        data.createdAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    );
  }

  public static Token toDomain(TokenDto dto) {
    Date accessTokenExpiry = Date.from(dto.accessTokenExpire().atZone(ZoneId.systemDefault()).toInstant());
    Date refreshTokenExpiry = dto.refreshTokenExpire() != null ?
        Date.from(dto.refreshTokenExpire().atZone(ZoneId.systemDefault()).toInstant()) : null;
    Date createdAt = Date.from(dto.issuedAt().atZone(ZoneId.systemDefault()).toInstant());

    TokenData tokenData = new TokenData(
        dto.accessToken(),
        accessTokenExpiry,
        dto.refreshToken(),
        refreshTokenExpiry,
        createdAt
    );

    return new Token(tokenData);
  }
}
