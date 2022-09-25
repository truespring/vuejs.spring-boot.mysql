package com.taskagile.domain.common.mail;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class SimpleMessage implements Message {
  private String to;
  private String subject;
  private String body;
  private String from;

  @Override
  public String getTo() {
    return to;
  }

  @Override
  public String getSubject() {
    return subject;
  }

  @Override
  public String getBody() {
    return body;
  }

  @Override
  public String getFrom() {
    return from;
  }
}
