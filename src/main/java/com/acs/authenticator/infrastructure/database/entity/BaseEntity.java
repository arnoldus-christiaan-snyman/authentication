package com.acs.authenticator.infrastructure.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity extends AbstractAggregateRoot<BaseEntity> implements ActiveStateEntity, PublishableEntity {

  @Column(nullable = false)
  private boolean isActive;
  private Timestamp createdAt;
  private Timestamp updatedAt;


  @PreUpdate
  public void onPreUpdate() {
    this.updatedAt = new Timestamp(System.currentTimeMillis());
  }

  @PrePersist
  public void onPrePersist() {
    Timestamp now = new Timestamp(System.currentTimeMillis());
    this.createdAt = now;
    this.updatedAt = now;
  }

  @PostPersist
  public void onPostPersist() {
    publishPersistEvent();
  }

  @PostUpdate
  public void onPostUpdate() {
    publishUpdateEvent();
  }

}
