package com.acs.authenticator.application.service;


import com.acs.authenticator.application.dto.TokenDto;
import com.acs.authenticator.application.dto.UserDto;
import java.util.Map;

public interface TokenService {
  boolean isValid(TokenDto token, UserDto user);
  boolean isExpired(TokenDto token);
  TokenDto generateToken(UserDto user);
  TokenDto generateToken(UserDto user, Map<String, Object> args);
}
