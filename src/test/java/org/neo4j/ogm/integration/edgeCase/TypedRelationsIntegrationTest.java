package org.neo4j.ogm.integration.edgeCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.ogm.integration.edgeCase.domain.Person;
import org.neo4j.ogm.integration.edgeCase.domain.Team;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.testutil.Neo4jIntegrationTestRule;

import java.io.IOException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author Nils Dr\u00F6ge
 */
public class TypedRelationsIntegrationTest {
    @Rule
    public Neo4jIntegrationTestRule neo4jRule = new Neo4jIntegrationTestRule();

    private Session session;

    @Before
    public void init() throws IOException {
        session = new SessionFactory("org.neo4j.ogm.integration.edgeCase.domain").openSession(neo4jRule.url());
    }

    @Test
    public void personHasNotLikedTeam() {
        Person person = new Person();
        session.save(person);

        Team team = new Team();
        person.ownedEntities.add(team);
        team.owner = person;
        session.save(team, 1);

        person = session.load(Person.class, person.id);

        assertThat(person.ownedEntities, hasItem(team));
        assertThat(person.likes, not(hasItem(team)));
    }
}