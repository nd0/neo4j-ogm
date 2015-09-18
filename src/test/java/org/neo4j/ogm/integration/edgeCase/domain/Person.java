package org.neo4j.ogm.integration.edgeCase.domain;

import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nils Dr\u00F6ge
 */
public class Person extends Entity {
    @Relationship(type = "OWN")
    public Set<OwnedEntity> ownedEntities = new HashSet<>();

    @Relationship(type = "LIKE")
    public Set<Entity> likes = new HashSet<>();
}
