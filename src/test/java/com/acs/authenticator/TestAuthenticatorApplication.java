package com.acs.authenticator;

import org.springframework.boot.SpringApplication;

public class TestAuthenticatorApplication {

	public static void main(String[] args) {
		SpringApplication.from(AuthenticatorApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
