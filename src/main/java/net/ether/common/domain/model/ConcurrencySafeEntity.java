package net.ether.common.domain.model;

public class ConcurrencySafeEntity extends Entity {

    private int concurrencyVersion;

    public int concurrencyVersion() {
        return this.concurrencyVersion;
    }

    public void setConcurrencyVersion(final int version) {
        this.failWhenConcurrencyViolation(version);
        this.concurrencyVersion = version;
    }

    public void failWhenConcurrencyViolation(final int version) {
        this.assertStateTrue(
                version == this.concurrencyVersion(),
                "Concurrency Violation: Stale data detected. Entity was already modified.");
    }
}
