package net.ether.common.domain.model;

import java.time.Instant;

public interface DomainEvent {

    int eventVersion();

    Instant occurredOn();

}
