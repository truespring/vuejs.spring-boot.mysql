package com.taskagile.domain.model.user;

import com.taskagile.domain.common.security.PasswordEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationManagementTests {

  private UserRepository repositoryMock;
  private PasswordEncryptor passwordEncryptorMock;
  private RegistrationManagement instance;

  @BeforeEach
  public void setUp() {
    repositoryMock = mock(UserRepository.class);
    passwordEncryptorMock = mock(PasswordEncryptor.class);
    instance = new RegistrationManagement(repositoryMock, passwordEncryptorMock);
  }

  @Test
  void register_existedUsername_shouldFail() throws RegistrationException {
    String username = "existUsername";
    String emailAddress = "truspring@gmail.com";
    String password = "MyPassword!";

    when(repositoryMock.findByUsername(username)).thenReturn(new User());
    assertThrows(UsernameExistsException.class, () -> {
      instance.register(username, emailAddress, password);
    });
  }

  @Test
  void register_existedEmailAddress_shouldFail() throws RegistrationException {
    String username = "rubok";
    String emailAddress = "exist@gmail.com";
    String password = "MyPassword!";

    when(repositoryMock.findByEmailAddress(emailAddress)).thenReturn(new User());
    assertThrows(EmailAddressExistsException.class, () -> {
      instance.register(username, emailAddress, password);
    });
  }

  @Test
  void register_uppercaseEmailAddress_shouldSucceedAndBecomeLowercase() throws RegistrationException {
    String username = "rubok";
    String emailAddress = "Truespring@Gmail.com";
    String password = "MyPassword!";
    instance.register(username, emailAddress, password);
    User userToSave = User.create(username, emailAddress.toLowerCase(), password);
    verify(repositoryMock).findByEmailAddress(userToSave.getEmailAddress());
  }
}
