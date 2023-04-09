package org.phoenix.demo.security.web;

import org.phoenix.demo.security.service.AuthenticationService;
import org.phoenix.demo.security.web.request.AuthenticationRequest;
import org.phoenix.demo.security.web.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final AuthenticationService service;


  public AuthenticationController(AuthenticationService service) {
    this.service = service;
  }


  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }
}
