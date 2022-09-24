package com.taskagile.domain.model.user;

import com.taskagile.domain.model.user.events.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserRegisteredEventHandler {

  @EventListener(UserRegisteredEvent.class)
  public void handleEvent(UserRegisteredEvent event) {
    log.debug("Handling `{}` registration event", event.getUser().getEmailAddress());
  }
}
