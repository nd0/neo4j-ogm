package org.neo4j.ogm.integration.edgeCase.domain;

import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nils Dr\u00F6ge
 */
public class Entity {
    public Long id;

    @Relationship(type = "LIKE", direction = Relationship.INCOMING)
    public Set<Person> likedBy = new HashSet<>();
}
