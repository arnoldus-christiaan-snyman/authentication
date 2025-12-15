package com.acs.authenticator.domain.repository;

import com.acs.authenticator.domain.model.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
  Optional<User> findByUsername(String username);
  Optional<User> findByAccountId(UUID accountId);
  User save(User user);
  void delete(UUID id);
}
