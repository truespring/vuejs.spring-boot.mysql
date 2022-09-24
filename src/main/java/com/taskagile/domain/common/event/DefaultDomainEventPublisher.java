package com.taskagile.domain.common.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultDomainEventPublisher implements DomainEventPublisher {

  private final ApplicationEventPublisher actualPublisher;

  @Override
  public void publish(final DomainEvent event) {
    actualPublisher.publishEvent(event);
  }

}
