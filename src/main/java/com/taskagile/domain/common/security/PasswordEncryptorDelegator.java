package com.taskagile.domain.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncryptorDelegator implements PasswordEncryptor {

  private final PasswordEncoder passwordEncoder;
  @Override
  public String encrypt(final String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }
}
