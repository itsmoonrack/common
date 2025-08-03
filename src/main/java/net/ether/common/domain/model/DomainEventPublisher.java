package net.ether.common.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public final class DomainEventPublisher {

    private static final ThreadLocal<DomainEventPublisher> instance = ThreadLocal.withInitial(DomainEventPublisher::new);

    private boolean publishing;
    @SuppressWarnings("rawtypes")
    private List subscribers;

    public static DomainEventPublisher instance() {
        return instance.get();
    }

    @SuppressWarnings({"unchecked"})
    public <T> void publish(final T domainEvent) {
        if (!this.isPublishing() && this.hasSubscribers()) {

            try {
                this.setPublishing(true);

                Class<?> eventType = domainEvent.getClass();

                Stream<DomainEventSubscriber<T>> allSubscribers = this.subscribers();

                allSubscribers
                        .filter(subscriber -> subscriber.subscribedToType().isAssignableFrom(eventType))
                        .forEach(subscriber -> subscriber.handleEvent(domainEvent));

            } finally {
                this.setPublishing(false);
            }
        }
    }

    public void publishAll(final Collection<DomainEvent> domainEvents) {
        domainEvents.forEach(this::publish);
    }

    public void reset() {
        if (!this.isPublishing()) {
            this.setSubscribers(null);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> void subscribe(final DomainEventSubscriber<T> subscriber) {
        if (!this.isPublishing()) {
            this.ensureSubscribersList();

            this.subscribers.add(subscriber);
        }
    }

    private DomainEventPublisher() {
        this.setPublishing(false);
        this.ensureSubscribersList();
    }

    @SuppressWarnings("rawtypes")
    private void ensureSubscribersList() {
        if (!this.hasSubscribers()) {
            this.setSubscribers(new ArrayList());
        }
    }

    private boolean isPublishing() {
        return this.publishing;
    }

    private void setPublishing(final boolean flag) {
        this.publishing = flag;
    }

    private boolean hasSubscribers() {
        return this.subscribers != null;
    }

    @SuppressWarnings("rawtypes")
    private Stream subscribers() {
        return this.subscribers.stream();
    }

    @SuppressWarnings("rawtypes")
    private void setSubscribers(final List subscribers) {
        this.subscribers = subscribers;
    }
}
