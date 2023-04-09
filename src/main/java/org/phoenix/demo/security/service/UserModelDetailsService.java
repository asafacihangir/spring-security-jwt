package org.phoenix.demo.security.service;

import java.util.List;
import org.phoenix.demo.security.domain.User;
import org.phoenix.demo.security.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class UserModelDetailsService implements
    UserDetailsService {

  private final UserRepository userRepository;

  public UserModelDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username)
        .map(user -> createSpringSecurityUser(username, user))
        .orElseThrow(() -> new UsernameNotFoundException(
            "User " + username + " was not found in the database"));
  }

  private org.springframework.security.core.userdetails.User createSpringSecurityUser(
      String email, User user) {
    if (!user.isActivated()) {
      throw new RuntimeException("User " + email + " was not activated");
    }
    List<GrantedAuthority> grantedAuthorities = List.of(
        new SimpleGrantedAuthority(user.getRole().toString()));

    return new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getPassword(),
        grantedAuthorities);
  }
}
