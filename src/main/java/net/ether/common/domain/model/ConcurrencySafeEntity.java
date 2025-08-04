package net.ether.common.domain.model;

public class ConcurrencySafeEntity extends Entity {

    private Long concurrencyVersion;

    public Long concurrencyVersion() {
        return this.concurrencyVersion;
    }
}
