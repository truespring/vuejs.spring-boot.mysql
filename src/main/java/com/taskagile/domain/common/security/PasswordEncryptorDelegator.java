package com.taskagile.domain.common.security;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptorDelegator implements PasswordEncryptor {

  @Override
  public String encrypt(final String rawPassword) {
    // TODO implement this
    return rawPassword;
  }
}
