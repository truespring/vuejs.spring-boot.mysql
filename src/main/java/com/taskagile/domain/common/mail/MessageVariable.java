package com.taskagile.domain.common.mail;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class MessageVariable {
  private String key;
  private Object value;

  public static MessageVariable from(final String key, final Object value) {
    return new MessageVariable(key, value);
  }

}
