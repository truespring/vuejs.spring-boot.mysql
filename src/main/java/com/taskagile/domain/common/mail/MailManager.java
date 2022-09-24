package com.taskagile.domain.common.mail;

public interface MailManager {
  void send(final String emailAddress, final  String subject, final String template, final MessageVariable... variables);
}
