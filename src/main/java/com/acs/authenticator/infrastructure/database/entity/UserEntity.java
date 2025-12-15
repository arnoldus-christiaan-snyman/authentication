package com.acs.authenticator.infrastructure.database.entity;

import com.acs.authenticator.domain.event.UserCreatedEvent;
import com.acs.authenticator.domain.event.UserModifiedEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = false)
  private AccountEntity account;

  @Column(nullable = false)
  private boolean isLocked;

  @Override
  public void publishUpdateEvent() {
    this.registerEvent(new UserModifiedEvent(this.id, this.username, this.password));
  }

  @Override
  public void publishPersistEvent(){
    this.registerEvent(new UserCreatedEvent(this.id, this.username, this.password));
  }
}
