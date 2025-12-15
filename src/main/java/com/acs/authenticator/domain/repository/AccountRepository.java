package com.acs.authenticator.domain.repository;

import com.acs.authenticator.domain.model.Account;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
  Optional<Account> findById(UUID id);
  Optional<Account> findByEmail(String email);
  Account save(Account account);
  void delete(UUID id);
}
