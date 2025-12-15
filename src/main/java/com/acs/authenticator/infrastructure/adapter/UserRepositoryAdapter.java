package com.acs.authenticator.infrastructure.adapter;

import com.acs.authenticator.domain.model.User;
import com.acs.authenticator.domain.model.valueobject.Password;
import com.acs.authenticator.domain.model.valueobject.Username;
import com.acs.authenticator.domain.repository.UserRepository;
import com.acs.authenticator.infrastructure.database.entity.AccountEntity;
import com.acs.authenticator.infrastructure.database.entity.UserEntity;
import com.acs.authenticator.infrastructure.database.repository.AccountEntityRepository;
import com.acs.authenticator.infrastructure.database.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

  private final UserEntityRepository userEntityRepository;
  private final AccountEntityRepository accountEntityRepository;

  @Override
  public Optional<User> findByUsername(String username) {
    return userEntityRepository.findByUsername(username)
        .map(this::toDomain);
  }

  @Override
  public Optional<User> findByAccountId(UUID accountId) {
    return userEntityRepository.findByAccountId(accountId)
        .map(this::toDomain);
  }

  @Override
  public User save(User user) {
    UserEntity entity = toEntity(user);
    UserEntity savedEntity = userEntityRepository.save(entity);
    return toDomain(savedEntity);
  }

  @Override
  public void delete(UUID id) {
    userEntityRepository.deleteById(id);
  }

  private User toDomain(UserEntity entity) {
    return new User(
        entity.getId(),
        entity.getAccount().getId(),
        new Username(entity.getUsername()),
        new Password(entity.getPassword()),
        entity.isLocked(),
        entity.isActive()
    );
  }

  private UserEntity toEntity(User user) {
    UserEntity entity = new UserEntity();
    entity.setId(user.id());
    entity.setUsername(user.username().value());
    entity.setPassword(user.password().value());

    // Load the account entity reference
    AccountEntity accountEntity = accountEntityRepository.getReferenceById(user.accountId());
    entity.setAccount(accountEntity);

    entity.setLocked(user.isLocked());
    entity.setActive(user.isActive());
    return entity;
  }
}
