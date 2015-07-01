package org.neo4j.ogm.defects;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.ogm.domain.refactor.Movie;
import org.neo4j.ogm.domain.refactor.Person;
import org.neo4j.ogm.domain.refactor.Rating;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.testutil.Neo4jIntegrationTestRule;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author vince
 */
public class RefactoringTest {

    @Rule
    public Neo4jIntegrationTestRule neo4jRule = new Neo4jIntegrationTestRule();

    private Session session;

    private Long idJim;
    private Long idMary;
    private Long idBill;

    @Before
    public void init() throws IOException {
        session = new SessionFactory("org.neo4j.ogm.domain.refactor").openSession(neo4jRule.url());

        createGraph();

    }

    @Test
    @Ignore("fails because there the LIKES relationship to Person is defined twice, albeit in different directions")
    public void shouldLoadLikesRelationships() {

        Person jim = session.load(Person.class, idJim);

        assertEquals(1, jim.peopleILike.size());
        assertEquals(idMary, jim.peopleILike.get(0).id);
    }

    @Test
    public void shouldHandleMultipleRatings() {

        Person bob = new Person();
        bob.name = "Bob";

        Movie matrix = new Movie();
        matrix.name = "The Matrix";

        Rating ratingOne = Rating.create(bob, matrix, 4);
        Rating ratingTwo = Rating.create(bob, matrix, 5);

        bob.movieRatings.add(ratingOne);
        bob.movieRatings.add(ratingTwo);

        //session.save(ratingOne);
        //session.save(ratingTwo);

        session.save(bob);

        session.clear();

        bob = session.load(Person.class, bob.id);
        assertEquals(2, bob.movieRatings.size());


    }

    private void createGraph() {

        Person jim = new Person();
        Person mary = new Person();
        Person bill = new Person();

        jim.name = "Jim";
        mary.name = "Mary";
        bill.name = "Bill";

        bill.peopleIFollow.add(jim);
        bill.peopleILike.add(mary);

        mary.peopleIFollow.add(bill);
        mary.peopleILike.add(jim);

        jim.peopleIFollow.add(mary);
        jim.peopleILike.add(mary);

        session.save(jim);

        idJim = jim.id;
        idBill = bill.id;
        idMary = mary.id;

        session.clear();

    }


}
