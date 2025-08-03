package net.ether.common;

import java.util.Objects;

public class AssertionConcern {

    protected AssertionConcern() {

    }

    protected void assertArgumentEquals(final Object a, final Object b, final String message) {
        if (!Objects.equals(a, b)) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentFalse(final boolean value, final String message) {
        if (value) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentLength(final String value, final int maximum, final String message) {
        int length = value.trim().length();
        if (length > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentLength(final String value, final int minimum, final int maximum, final String message) {
        int length = value.trim().length();
        if (length < minimum || length > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentNotEmpty(final String value, final String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentNotEquals(final Object a, final Object b, final String message) {
        if (Objects.equals(a, b)) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentNotNull(final Object value, final String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(final double value, final double minimum, final double maximum, final String message) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(final float value, final float minimum, final float maximum, final String message) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(final int value, final int minimum, final int maximum, final String message) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(final long value, final long minimum, final long maximum, final String message) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentTrue(final boolean value, final String message) {
        if (!value) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertStateFalse(final boolean value, final String message) {
        if (value) {
            throw new IllegalStateException(message);
        }
    }

    protected void assertStateTrue(final boolean value, final String message) {
        if (!value) {
            throw new IllegalStateException(message);
        }
    }
}