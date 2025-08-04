package net.ether.common.domain.model;

import net.ether.common.AssertionConcern;

public class IdentifiedDomainObject extends AssertionConcern {

    private Long id;

    protected IdentifiedDomainObject() {

    }

    protected long id() {
        return this.id;
    }
}
