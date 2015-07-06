/*
 * Copyright (c) 2002-2015 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with
 * separate copyright notices and license terms. Your use of the source
 * code for these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 *
 */

package org.neo4j.ogm.mapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility to help group elements of a common type into a single collection (by relationship type and direction) to be set on an owning object.
 * The ability to set a collection of instances on an owning entity based on the type of instance is insufficient as described in DATAGRAPH-637 and DATAGRAPH-636.
 * The relationship type and direction are required to be able to correctly determine which instances are to be set for which property of the node entity.
 * @author Adam George
 * @author Luanne Misquitta
 */
class EntityCollector {

    private final Logger logger = LoggerFactory.getLogger(EntityCollector.class);
    private final Map<Object, Map<Relationship, Set<Object>>> relationshipCollectibles = new HashMap<>();

    /**
     * Adds the given collectible element into a collection based on relationship type and direction ready to be set on the given owning type.
     *
     * @param owningEntity          The type on which the collection is to be set
     * @param collectibleElement    The element to add to the collection that will eventually be set on the owning type
     * @param relationshipType      The relationship type that this collection corresponds to
     * @param relationshipDirection The relationship direction
     */
    public void recordTypeRelationship(Object owningEntity, Object collectibleElement, String relationshipType, String relationshipDirection) {
        if (this.relationshipCollectibles.get(owningEntity) == null) {
            this.relationshipCollectibles.put(owningEntity, new HashMap<Relationship, Set<Object>>());
        }
        Relationship relationship = new Relationship(relationshipType,relationshipDirection);
        if (this.relationshipCollectibles.get(owningEntity).get(relationship) == null) {
            this.relationshipCollectibles.get(owningEntity).put(relationship, new HashSet<Object>());
        }
        this.relationshipCollectibles.get(owningEntity).get(relationship).add(collectibleElement);
    }

    /**
     * @return All the owning types that have been added to this {@link EntityCollector}
     */
    public Iterable<Object> getOwningTypes() {
        return this.relationshipCollectibles.keySet();
    }

    /**
     * Retrieves all relationship types for which collectibles can be set on an owning object
     *
     * @param owningObject the owning object
     * @return all relationship types owned by the owning object
     */
    public Iterable<String> getOwningRelationshipTypes(Object owningObject) {
        Set<String> relTypes = new HashSet<>();
        for(Relationship rel : this.relationshipCollectibles.get(owningObject).keySet()) {
            relTypes.add(rel.relationshipType);
        }
        return relTypes;
    }

    public Iterable<String> getRelationshipDirectionsForOwningTypeAndRelationshipType(Object owningObject, String relationshipType) {
        Set<String> relDirections = new HashSet<>();
        for(Relationship rel : this.relationshipCollectibles.get(owningObject).keySet()) {
            if(rel.relationshipType.equals(relationshipType)) {
                relDirections.add(rel.relationshipDirection);
            }
        }
        return relDirections;
    }
    /**
     * A set of collectibles based on relationship type for an owning object
     *
     * @param owningObject the owning object
     * @param relationshipType the relationship type
     * @return set of instances to be set for the relationship type on the owning object
     */
    public Set<Object> getCollectiblesForOwnerAndRelationship(Object owningObject, String relationshipType, String relationshipDirection) {
        Relationship relationship = new Relationship(relationshipType,relationshipDirection);
        return this.relationshipCollectibles.get(owningObject).get(relationship);
    }

    /**
     * Get the type of the instance to be set on the owner object
     *
     * @param owningObject the owner object
     * @param relationshipType the relationship type
     * @param relationshipDirection the relationship direction
     * @return type of instance
     */
    public Class getCollectibleTypeForOwnerAndRelationship(Object owningObject, String relationshipType, String relationshipDirection) {
        Relationship relationship = new Relationship(relationshipType,relationshipDirection);
        return this.relationshipCollectibles.get(owningObject).get(relationship).iterator().next().getClass();
    }

    class Relationship {
        String relationshipType;
        String relationshipDirection;

        public Relationship(String relationshipType, String relationshipDirection) {
            this.relationshipType = relationshipType;
            this.relationshipDirection = relationshipDirection;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Relationship that = (Relationship) o;

            if (!relationshipType.equals(that.relationshipType)) return false;
            return relationshipDirection.equals(that.relationshipDirection);
        }

        @Override
        public int hashCode() {
            int result = relationshipType.hashCode();
            result = 31 * result + relationshipDirection.hashCode();
            return result;
        }
    }
}
