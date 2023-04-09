package org.phoenix.demo.security.service;

import org.phoenix.demo.security.domain.Role;
import org.phoenix.demo.security.domain.User;
import org.phoenix.demo.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  public SimpleUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }


  public void saveUser(){
    final User user = new User();
    user.setFirstname("Safa");
    user.setLastname("Cihangir");
    user.setEmail("asafacihangir@gmail.com");
    user.setActivated(true);
    user.setPassword(passwordEncoder.encode("admin"));
    user.setRole(Role.ADMIN);
    userRepository.save(user);
  }

}
