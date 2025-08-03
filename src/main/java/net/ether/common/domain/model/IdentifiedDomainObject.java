package net.ether.common.domain.model;

import net.ether.common.AssertionConcern;

import java.io.Serializable;

public class IdentifiedDomainObject extends AssertionConcern implements Serializable {

    private Long id;

    protected IdentifiedDomainObject() {

    }

    protected long id() {
        return this.id;
    }
}
