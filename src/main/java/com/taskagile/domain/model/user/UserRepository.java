package com.taskagile.domain.model.user;

public interface UserRepository {

  User findByUsername(final String username);
  User findByEmailAddress(final String emailAddress);
  void save(final User user);
}
