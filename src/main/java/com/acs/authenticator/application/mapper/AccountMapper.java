package com.acs.authenticator.application.mapper;

import com.acs.authenticator.application.dto.AccountDto;
import com.acs.authenticator.domain.model.Account;
import com.acs.authenticator.domain.model.valueobject.Email;
import com.acs.authenticator.domain.model.valueobject.Localization;

public final class AccountMapper {

  private AccountMapper() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static AccountDto toDto(Account account) {
    return new AccountDto(
        account.id(),
        account.name(),
        account.surname(),
        account.email().value(),
        account.role(),
        account.localization().local(),
        account.isActive()
    );
  }

  public static Account toDomain(AccountDto dto) {
    return new Account(
        dto.id(),
        dto.name(),
        dto.surname(),
        new Email(dto.email()),
        dto.role(),
        new Localization(dto.localization()),
        dto.isActive()
    );
  }
}
