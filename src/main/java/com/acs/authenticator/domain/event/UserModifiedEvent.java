package com.acs.authenticator.domain.event;

import java.util.UUID;

public record UserModifiedEvent(UUID id, String username, String password) {

}
