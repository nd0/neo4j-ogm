package org.neo4j.ogm.domain.refactor;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vince
 */
@NodeEntity(label="Movie")
public class Movie extends Entity {

    @Relationship(type="RATED", direction= Relationship.INCOMING)
    private List<Rating> ratings = new ArrayList<>();
}
