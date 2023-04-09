package org.phoenix.demo.security.repository;

import java.util.Optional;
import org.phoenix.demo.base.BaseRepository;
import org.phoenix.demo.security.domain.User;

public interface UserRepository extends BaseRepository<User, String> {

  Optional<User> findByEmail(String email);

}
