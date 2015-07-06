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

package org.neo4j.ogm.unit.entityaccess.relationships;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.*;
import org.neo4j.ogm.domain.entityMapping.Movie;
import org.neo4j.ogm.domain.entityMapping.Person;
import org.neo4j.ogm.domain.entityMapping.Rating;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.testutil.Neo4jIntegrationTestRule;

/**
 * @author vince
 * @author Luanne Misquitta
 */
public class MultipleRelationshipsTest {

    @Rule
    public Neo4jIntegrationTestRule neo4jRule = new Neo4jIntegrationTestRule();

    private Session session;

    private Long idJim;
    private Long idMary;
    private Long idBill;
    private Long idMatrix;
    private Long idDieHard;

    @Before
    public void init() throws IOException {
        session = new SessionFactory("org.neo4j.ogm.domain.entityMapping").openSession(neo4jRule.url());
        createGraph();
    }

    @After
    public void tearDown() {
        neo4jRule.clearDatabase();
        neo4jRule.shutdown();
    }



    @Test
    public void shouldLoadLikesRelationships() {
        Person jim = session.load(Person.class, idJim);

        assertEquals(1, jim.peopleILike.size());
        assertEquals(idMary, jim.peopleILike.get(0).id);
    }

    @Test
    @Ignore //TODO fix this
    public void shouldCreateGraphProperly() {

        Person jim = session.load(Person.class, idJim);
        assertEquals(2, jim.movieRatings.size());
        assertEquals(1, jim.peopleILike.size());
        assertEquals(1, jim.peopleWhoLikeMe.size());
        assertEquals(1, jim.peopleIFollow.size());
        assertEquals(2, jim.peopleWhoFollowMe.size());

        Person bill = session.load(Person.class, idBill);
        assertEquals(2, bill.movieRatings.size());
        assertEquals(1, bill.peopleILike.size());
        assertEquals(0, bill.peopleWhoLikeMe.size());
        assertEquals(1, bill.peopleIFollow.size());
        assertEquals(1, bill.peopleWhoFollowMe.size());

        Person mary = session.load(Person.class, idMary);
        Movie dieHard = session.load(Movie.class, idDieHard);
        Movie matrix = session.load(Movie.class, idMatrix);
        assertEquals(1, mary.movieRatings.size());
        assertEquals(1, mary.peopleILike.size());
        assertEquals(2, mary.peopleWhoLikeMe.size());
        assertEquals(2, mary.peopleIFollow.size());
        assertEquals(1, mary.peopleWhoFollowMe.size());


        assertEquals(3, matrix.ratings.size());
        assertEquals(2, dieHard.ratings.size());
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
        bill.peopleWhoFollowMe.add(mary);

        mary.peopleIFollow.add(bill);
        mary.peopleIFollow.add(jim);
        mary.peopleILike.add(jim);
        mary.peopleWhoLikeMe.add(bill);
        mary.peopleWhoFollowMe.add(jim);
        mary.peopleWhoLikeMe.add(jim);

        jim.peopleIFollow.add(mary);
        jim.peopleILike.add(mary);
        jim.peopleWhoFollowMe.add(bill);
        jim.peopleWhoFollowMe.add(mary);
        jim.peopleWhoLikeMe.add(mary);

        session.save(jim);

        idJim = jim.id;
        idBill = bill.id;
        idMary = mary.id;

        Movie matrix = new Movie();
        matrix.name = "The Matrix";

        Movie dieHard = new Movie();
        dieHard.name = "Die Hard";

        Rating ratingOne = Rating.create(bill, matrix, 4);
        Rating ratingTwo = Rating.create(bill, matrix, 5);
        Rating ratingThree = Rating.create(jim, matrix, 3);
        Rating ratingFour = Rating.create(jim, dieHard, 5);
        Rating ratingFive = Rating.create(mary, dieHard, 5);

        bill.movieRatings.add(ratingOne);
        matrix.ratings.add(ratingOne);

        bill.movieRatings.add(ratingTwo);
        matrix.ratings.add(ratingTwo);

        jim.movieRatings.add(ratingThree);
        matrix.ratings.add(ratingThree);

        jim.movieRatings.add(ratingFour);
        dieHard.ratings.add(ratingFour);

        mary.movieRatings.add(ratingFive);
        dieHard.ratings.add(ratingFive);

        session.save(bill);
        session.save(jim);
        session.save(mary);

        idDieHard = dieHard.id;
        idMatrix = matrix.id;

        session.clear();

    }


}
