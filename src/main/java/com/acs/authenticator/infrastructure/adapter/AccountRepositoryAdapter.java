package com.acs.authenticator.infrastructure.adapter;

import com.acs.authenticator.domain.model.Account;
import com.acs.authenticator.domain.model.Role;
import com.acs.authenticator.domain.model.valueobject.Email;
import com.acs.authenticator.domain.model.valueobject.Localization;
import com.acs.authenticator.domain.repository.AccountRepository;
import com.acs.authenticator.infrastructure.database.entity.AccountEntity;
import com.acs.authenticator.infrastructure.database.repository.AccountEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {

  private final AccountEntityRepository accountEntityRepository;

  @Override
  public Optional<Account> findById(UUID id) {
    return accountEntityRepository.findById(id)
        .map(this::toDomain);
  }

  @Override
  public Optional<Account> findByEmail(String email) {
    return accountEntityRepository.findByEmail(email)
        .map(this::toDomain);
  }

  @Override
  public Account save(Account account) {
    AccountEntity entity = toEntity(account);
    AccountEntity savedEntity = accountEntityRepository.save(entity);
    //TODO: Publish AccountCreatedEvent
    return toDomain(savedEntity);
  }

  @Override
  public void delete(UUID id) {
    accountEntityRepository.deleteById(id);
  }

  private Account toDomain(AccountEntity entity) {
    return new Account(
        entity.getId(),
        entity.getName(),
        entity.getSurname(),
        new Email(entity.getEmail()),
        Role.valueOf(entity.getRole()),
        new Localization(Locale.of(entity.getLanguage(), entity.getCountry())),
        entity.isActive()
    );
  }

  private AccountEntity toEntity(Account account) {
    AccountEntity entity = new AccountEntity();
    entity.setId(account.id());
    entity.setName(account.name());
    entity.setSurname(account.surname());
    entity.setEmail(account.email().value());
    entity.setRole(account.role().name());
    entity.setLanguage(account.localization().local().getLanguage());
    entity.setCountry(account.localization().local().getCountry());
    entity.setActive(account.isActive());
    return entity;
  }
}
