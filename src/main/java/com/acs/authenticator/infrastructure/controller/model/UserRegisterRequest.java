package com.acs.authenticator.infrastructure.controller.model;

public record UserRegisterRequest(
    String username,
    String password,
    String email,
    String fullName,
    String surname
) {

}
