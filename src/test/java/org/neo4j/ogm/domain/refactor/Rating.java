package org.neo4j.ogm.domain.refactor;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * @author vince
 */
@RelationshipEntity(type="RATED")
public class Rating {

    public Long id;

    @StartNode
    public Person person;

    @EndNode
    public Movie movie;

    public int value;

    public static Rating create(Person person, Movie movie, int value) {
        Rating rating = new Rating();
        rating.person = person;
        rating.movie = movie;
        rating.value = value;
        return rating;
    }
}
