package com.taskagile.infrastructure.mail;

import com.taskagile.domain.common.mail.Mailer;
import com.taskagile.domain.common.mail.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component
public class AsyncMailer implements Mailer {

  private final JavaMailSender mailSender;

  public AsyncMailer(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Async
  @Override
  public void send(Message message) {
    Assert.notNull(message, "Parameter `message` must not be null");

    try {
      SimpleMailMessage mailMessage = new SimpleMailMessage();

      if (!message.getFrom().isBlank()) {
        mailMessage.setFrom(message.getFrom());
      }
      if (!message.getSubject().isBlank()) {
        mailMessage.setSubject(message.getSubject());
      }
      if (!message.getBody().isBlank()) {
        mailMessage.setText(message.getBody());
      }
      if (!message.getTo().isBlank()) {
        mailMessage.setTo(message.getTo());
      }

      mailSender.send(mailMessage);
    } catch (MailException e) {
      log.error("Failed to send to mail message", e);
    }
  }
}
