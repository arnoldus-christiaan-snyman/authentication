package com.acs.authenticator.infrastructure.database.entity;

import java.sql.Timestamp;

public interface ActiveStateEntity {
  boolean isActive();
  Timestamp getCreatedAt();
  Timestamp getUpdatedAt();
}
