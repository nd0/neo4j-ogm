package org.neo4j.ogm.integration.edgeCase.domain;

import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Nils Dr\u00F6ge
 */
public class Team extends Entity implements OwnedEntity {
    @Relationship(type = "OWN", direction = Relationship.INCOMING)
    public Person owner;
}
