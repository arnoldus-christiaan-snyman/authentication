package com.acs.authenticator.infrastructure.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.Set;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonRootName(value = "response")
public class ApiResponse {
  @JsonProperty
  private final Set<ApiError> errors;

  public ApiResponse() {
    this.errors = Set.of();
  }

  public ApiResponse(Set<ApiError> errors) {
    this.errors = errors == null ? Set.of() : errors;
  }

  public ApiResponse(ApiError error) {
    this.errors = error == null ? Set.of() : Set.of(error);
  }
}
