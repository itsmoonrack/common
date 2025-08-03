package net.ether.common.domain.model;

import net.ether.common.AssertionConcern;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractId extends AssertionConcern implements Identity, Serializable {

    private String id;

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractId that = (AbstractId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{id='" + id + "'}";
    }

    protected AbstractId(final String id) {
        this.setId(id);
    }

    protected AbstractId() {
        super();
    }

    protected void validateId(final String id) {
        // implemented by subclasses for validation.
        // throws a runtime exception if invalid.
    }

    private void setId(final String id) {
        this.assertArgumentNotEmpty(id, "The basic identity is required.");
        this.assertArgumentLength(id, 36, "The basic identity must be 36 characters.");

        this.validateId(id);

        this.id = id;
    }
}
