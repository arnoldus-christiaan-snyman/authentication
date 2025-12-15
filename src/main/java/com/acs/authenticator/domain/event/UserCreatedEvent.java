package com.acs.authenticator.domain.event;

import java.util.UUID;

public record UserCreatedEvent(UUID id, String username, String password) {

}
