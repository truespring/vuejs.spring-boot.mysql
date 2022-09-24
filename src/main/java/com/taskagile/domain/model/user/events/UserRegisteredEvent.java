package com.taskagile.domain.model.user.events;

import com.taskagile.domain.common.event.DomainEvent;
import com.taskagile.domain.model.user.User;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class UserRegisteredEvent extends DomainEvent {

  private static final long serialVersionUID = 2580061707540917880L;

  private final User user;

  public UserRegisteredEvent(final User user) {
    super(user);
    Assert.notNull(user, "Parameter `user` must not be null");
    this.user = user;
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserRegisteredEvent that = (UserRegisteredEvent) o;
    return that.user.equals(this.user);
  }

  public int hashCode() {
    return this.user.hashCode();
  }

  public String toString() {
    return "UserRegisteredEvent{" +
      "user='" + user + '\'' +
      "timestamp='" + getTimestamp() + '\'' +
      '}';
  }
}
