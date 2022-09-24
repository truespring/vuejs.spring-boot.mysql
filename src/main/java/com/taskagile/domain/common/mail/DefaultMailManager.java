package com.taskagile.domain.common.mail;

import org.springframework.stereotype.Component;

@Component
public class DefaultMailManager implements MailManager {

  @Override
  public void send(final String emailAddress, final String subject, final String template, final MessageVariable... variables) {
    // TODO implement this
  }
}
