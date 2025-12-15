package com.acs.authenticator.infrastructure.database.entity;

public interface PublishableEntity {
  void publishUpdateEvent();
  void publishPersistEvent();
}
