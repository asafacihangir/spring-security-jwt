package org.phoenix.demo.security.service;

import org.phoenix.demo.security.web.request.AuthenticationRequest;
import org.phoenix.demo.security.web.response.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;


  public AuthenticationService(JwtService jwtService,
      AuthenticationManager authenticationManager) {
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    final Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.email(), request.password()));

    String jwtToken = jwtService.createToken(authentication);
    return new AuthenticationResponse(jwtToken);
  }


}
