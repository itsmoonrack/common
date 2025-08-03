package net.ether.common.domain.model;

import net.ether.common.AssertionConcern;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class EventSourcedRootEntity extends AssertionConcern {
    private static final ConcurrentMap<
            Class<? extends EventSourcedRootEntity>, ConcurrentMap<
            Class<? extends DomainEvent>, Consumer<? extends DomainEvent>>> mutatorMethods = new ConcurrentHashMap<>();

    private List<DomainEvent> mutatingEvents;
    private int unmutatedVersion;

    public int mutatedVersion() {
        return this.unmutatedVersion() + 1;
    }

    public List<DomainEvent> mutatingEvents() {
        return this.mutatingEvents;
    }

    public int unmutatedVersion() {
        return this.unmutatedVersion;
    }

    protected EventSourcedRootEntity(
            final Stream<DomainEvent> eventStream,
            final int streamVersion) {

        eventStream.forEach(this::mutateWhen);
        this.setUnmutatedVersion(streamVersion);
    }

    protected EventSourcedRootEntity() {
        this.setMutatingEvents(new ArrayList<>(2));
        this.setUnmutatedVersion(0);
    }

    protected void apply(final DomainEvent domainEvent) {

        this.mutatingEvents().add(domainEvent);

        this.mutateWhen(domainEvent);
    }

    protected static <T extends DomainEvent> void mutate(
            final Class<? extends EventSourcedRootEntity> rootType,
            final Class<T> eventType,
            final Consumer<T> mutatorMethod) {
        mutatorMethods
                .computeIfAbsent(rootType, k -> new ConcurrentHashMap<>())
                .put(eventType, mutatorMethod);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void mutateWhen(final DomainEvent domainEvent) {

        final Class<? extends EventSourcedRootEntity> rootType = this.getClass();

        final Class<? extends DomainEvent> eventType = domainEvent.getClass();

        final Consumer mutatorMethod = mutatorMethods.get(rootType).get(eventType);

        mutatorMethod.accept(domainEvent);
    }

    private void setMutatingEvents(final List<DomainEvent> mutatingEvents) {
        this.mutatingEvents = mutatingEvents;
    }

    private void setUnmutatedVersion(final int streamVersion) {
        this.unmutatedVersion = streamVersion;
    }
}
