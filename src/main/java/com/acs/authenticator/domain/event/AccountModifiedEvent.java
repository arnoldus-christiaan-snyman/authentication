package com.acs.authenticator.domain.event;

import java.util.UUID;

public record AccountModifiedEvent(UUID id, String name, String surname, String email, String role) {

}
