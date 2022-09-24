package com.taskagile.domain.application.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class RegistrationCommand {

  private final String username;
  private final String emailAddress;
  private final String password;

  public RegistrationCommand(final String username, final String emailAddress, final String password) {
    this.username = username;
    this.emailAddress = emailAddress;
    this.password = password;
  }
}
