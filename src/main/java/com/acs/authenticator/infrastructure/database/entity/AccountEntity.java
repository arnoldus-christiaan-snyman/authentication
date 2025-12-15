package com.acs.authenticator.infrastructure.database.entity;

import com.acs.authenticator.domain.event.AccountCreatedEvent;
import com.acs.authenticator.domain.event.AccountModifiedEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String surname;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String role;

  @Column(nullable = false)
  private String language;

  @Column(nullable = false)
  private String country;

  @Override
  public void publishUpdateEvent() {
    this.registerEvent(new AccountModifiedEvent(this.id, this.name, this.surname, this.email, this.role));
  }

  @Override
  public void publishPersistEvent() {
    this.registerEvent(new AccountCreatedEvent(this.id, this.name, this.surname, this.email, this.role));
  }
}
