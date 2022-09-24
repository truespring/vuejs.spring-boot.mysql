package com.taskagile.domain.common.event;

public interface DomainEventPublisher {
  void publish(final DomainEvent event);
}
