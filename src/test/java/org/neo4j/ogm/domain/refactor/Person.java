package org.neo4j.ogm.domain.refactor;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.RelationshipEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vince
 */
@NodeEntity(label="Person")
public class Person extends Entity {

    @Relationship(type="RATED", direction=Relationship.OUTGOING)
    public List<Rating> movieRatings = new ArrayList<>();

    @Relationship(type="LIKES", direction=Relationship.OUTGOING)
    public List<Person> peopleILike = new ArrayList<>();

    @Relationship(type="LIKES", direction=Relationship.INCOMING)
    public List<Person> peopleWhoLikeMe = new ArrayList<>();

    @Relationship(type="FOLLOWS", direction=Relationship.OUTGOING)
    public List<Person> peopleIFollow = new ArrayList<>();

    @Relationship(type="FOLLOWS", direction=Relationship.INCOMING)
    public List<Person> peopleWhoFollowMe = new ArrayList<>();


}
