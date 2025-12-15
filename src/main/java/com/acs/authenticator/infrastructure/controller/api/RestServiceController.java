package com.acs.authenticator.infrastructure.controller.api;

import com.acs.authenticator.infrastructure.controller.model.ApiResponse;
import com.acs.authenticator.infrastructure.controller.model.UserRegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestServiceController {

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/user/register")
  public ApiResponse registerUser(@RequestBody UserRegisterRequest request) {
    // Registration logic would go here
    return new ApiResponse();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/auth")
  public ApiResponse authentication(@RequestBody UserRegisterRequest request) {
    //TODO : Implement authentication logic
    // Registration logic would go here
    return new ApiResponse();
  }

}
