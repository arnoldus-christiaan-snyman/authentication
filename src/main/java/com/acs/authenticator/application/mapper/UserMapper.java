package com.acs.authenticator.application.mapper;

import com.acs.authenticator.application.dto.UserDto;
import com.acs.authenticator.domain.model.User;
import com.acs.authenticator.domain.model.valueobject.Password;
import com.acs.authenticator.domain.model.valueobject.Username;

public final class UserMapper {

  private UserMapper() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static UserDto toDto(User user) {
    return new UserDto(
        user.id(),
        user.accountId(),
        user.username().value(),
        user.password().value(),
        user.isLocked(),
        user.isActive()
    );
  }

  public static User toDomain(UserDto dto) {
    return new User(
        dto.id(),
        dto.accountId(),
        new Username(dto.username()),
        new Password(dto.password()),
        dto.isLocked(),
        dto.isActive()
    );
  }
}
