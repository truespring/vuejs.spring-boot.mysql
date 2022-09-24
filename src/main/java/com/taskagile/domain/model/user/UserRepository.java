package com.taskagile.domain.model.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(final String username);
  User findByEmailAddress(final String emailAddress);
}
