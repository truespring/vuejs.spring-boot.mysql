package com.taskagile.domain.model.user;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTests {

  @Autowired
  private UserRepository repository;

  @Test
  void save_nullUsernameUser_shouldFail() {
    User invalidUser = User.create(null, "truespring@gmail.com", "MyPassword!");
    assertThrows(DataIntegrityViolationException.class, () -> {
      repository.save(invalidUser);
    });
  }

  @Test
  void save_nullEmailAddressUser_shouldFail() {
    User invalidUser = User.create("rubok", null, "MyPassword!");
    assertThrows(DataIntegrityViolationException.class, () -> {
      repository.save(invalidUser);
    });
  }

  @Test
  void save_nullPasswordUser_shouldFail() {
    User invalidUser = User.create("rubok", "truespring@gmail.com", null);
    assertThrows(DataIntegrityViolationException.class, () -> {
      repository.save(invalidUser);
    });
  }

  @Test
  void save_validUser_shouldSuccess() {
    String username = "rubok";
    String emailAddress = "truespring@gmail.com";
    User newUser = User.create(username, emailAddress, "MyPassword!");
    repository.save(newUser);
    assertNotNull("New user's id should be generated", String.valueOf(newUser.getId()));
    assertNotNull("New user's created date should be generated", String.valueOf(newUser.getCreatedDate()));
    assertEquals(username, newUser.getUsername());
    assertEquals(emailAddress, newUser.getEmailAddress());
    assertEquals("", newUser.getFirstName());
    assertEquals("", newUser.getLastName());
  }

  @Test
  void save_usernameAlreadyExist_shouldFail() {
    // Create already exist user
    String username = "rubok";
    String emailAddress = "truespring@gmail.com";
    User alreadyExist = User.create(username, emailAddress, "MyPassword!");
    repository.save(alreadyExist);

    try {
      User newUser = User.create(username, "new@taskagile.com", "MyPassword!");
      repository.save(newUser);
    } catch (Exception e) {
      assertEquals(ConstraintViolationException.class.toString(), e.getCause().getClass().toString());
    }
  }

  @Test
  void save_emailAddressAlreadyExist_shouldFail() {
    // Create already exist user
    String username = "rubok";
    String emailAddress = "truespring@gmail.com";
    User alreadyExist = User.create(username, emailAddress, "MyPassword!");
    repository.save(alreadyExist);

    try {
      User newUser = User.create("new", emailAddress, "MyPassword!");
      repository.save(newUser);
    } catch (Exception e) {
      assertEquals(ConstraintViolationException.class.toString(), e.getCause().getClass().toString());
    }
  }

  @Test
  void findByEmailAddress_notExist_shouldReturnEmptyResult() {
    String emailAddress = "truespring@gmail.com";
    User user = repository.findByEmailAddress(emailAddress);
    assertNull(user, "No user should by found");
  }

  @Test
  void findByEmailAddress_exist_shouldReturnResult() {
    String emailAddress = "truespring@gmail.com";
    String username = "rubok";
    User newUser = User.create(username, emailAddress, "MyPassword!");
    repository.save(newUser);
    User found = repository.findByEmailAddress(emailAddress);
    assertEquals(username, found.getUsername(), "Username should match");
  }

  @Test
  void findByUsername_notExist_shouldReturnEmptyResult() {
    String username = "rubok";
    User user = repository.findByUsername(username);
    assertNull(user, "No user should by found");
  }

  @Test
  void findByUsername_exist_shouldReturnResult() {
    String username = "rubok";
    String emailAddress = "truespring@gmail.com";
    User newUser = User.create(username, emailAddress, "MyPassword!");
    repository.save(newUser);
    User found = repository.findByUsername(username);
    assertEquals(emailAddress, found.getEmailAddress(), "Email address should match");
  }
}
