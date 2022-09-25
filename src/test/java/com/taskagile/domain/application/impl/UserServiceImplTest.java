package com.taskagile.domain.application.impl;

import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.common.mail.MailManager;
import com.taskagile.domain.common.mail.MessageVariable;
import com.taskagile.domain.model.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

  private RegistrationManagement registrationManagementMock;
  private DomainEventPublisher eventPublisherMock;
  private MailManager mailManagerMock;
  private UserRepository repositoryMock;
  private UserServiceImpl instance;

  @BeforeEach
  public void setUp() {
    registrationManagementMock = mock(RegistrationManagement.class);
    eventPublisherMock = mock(DomainEventPublisher.class);
    mailManagerMock = mock(MailManager.class);
    repositoryMock = mock(UserRepository.class);
    instance = new UserServiceImpl(registrationManagementMock, eventPublisherMock, mailManagerMock, repositoryMock);
  }

  @Test
  void register_null_nullCommand_shouldFail() throws RegistrationException {
    assertThrows(IllegalArgumentException.class, () -> {
      instance.register(null);
    });
  }

  @Test
  void register_existingUsername_shouldFail() throws RegistrationException {
    String username = "existing";
    String emailAddress = "truespring@gmail.com";
    String password = "MyPassword!";
    doThrow(UsernameExistsException.class).when(registrationManagementMock).register(username, emailAddress, password);

    RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);
    assertThrows(RegistrationException.class, () -> {
      instance.register(command);
    });
  }

  @Test
  void register_existingEmailAddress_shouldFail() throws RegistrationException {
    String username = "rubok";
    String emailAddress = "existing@gmail.com";
    String password = "MyPassword!";
    doThrow(EmailAddressExistsException.class).when(registrationManagementMock).register(username, emailAddress, password);

    RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);
    assertThrows(RegistrationException.class, () -> {
      instance.register(command);
    });
  }

  @Test
  void register_validCommand_shouldSucceed() throws RegistrationException {
    String username = "rubok";
    String emailAddress = "truespring@gmail.com";
    String password = "MyPassword!";
    User newUser = User.create(username, emailAddress, password);
    when(registrationManagementMock.register(username, emailAddress, password)).thenReturn(newUser);
    RegistrationCommand command = new RegistrationCommand(username, emailAddress, password);

    instance.register(command);

    verify(mailManagerMock).send(
      emailAddress,
      "Welcome to TaskAgile",
      "welcome.ftl",
      MessageVariable.from("user", newUser)
    );
  }
}
