package com.taskagile.domain.application.impl;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.common.event.DomainEventPublisher;
import com.taskagile.domain.common.mail.MailManager;
import com.taskagile.domain.common.mail.MessageVariable;
import com.taskagile.domain.model.user.*;
import com.taskagile.domain.model.user.events.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final RegistrationManagement registrationManagement;
  private final DomainEventPublisher domainEventPublisher;
  private final MailManager mailManager;
  private final UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    if (username.isEmpty()) {
      throw new UsernameNotFoundException("No user found");
    }
    User user;
    if (username.contains("@")) {
      user = userRepository.findByEmailAddress(username);
    } else {
      user = userRepository.findByUsername(username);
    }
    if (user == null) {
      throw new UsernameNotFoundException("No user found by `" + username + "`");
    }
    return new SimpleUser(user);
  }

  @Override
  public void register(final RegistrationCommand command) throws RegistrationException {
    Assert.notNull(command, "Parameter `command` must not be null");
    final User newUser = registrationManagement.register(
      command.getUsername(),
      command.getEmailAddress(),
      command.getPassword()
    );

    sendWelcomeMessage(newUser);
    domainEventPublisher.publish(new UserRegisteredEvent(newUser));
    log.info("Check encryped password {}", newUser.getPassword());
    userRepository.save(newUser);
  }

  private void sendWelcomeMessage(final User user) {
    mailManager.send(
      user.getEmailAddress(),
      "Welcome to TaskAgile",
      "welcome.ftl",
      MessageVariable.from("user", user)
    );
  }
}
