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
package org.neo4j.ogm.integration.mappings;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.ogm.domain.mappings.Article;
import org.neo4j.ogm.domain.mappings.Person;
import org.neo4j.ogm.domain.mappings.RichRelation;
import org.neo4j.ogm.domain.mappings.Tag;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.testutil.Neo4jIntegrationTestRule;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertSame;

/**
 * @author Nils Dröge
 */
public class RichRelationTest {
    @Rule
    public Neo4jIntegrationTestRule neo4jRule = new Neo4jIntegrationTestRule();

    private Session session;

    @Before
    public void init() throws IOException {
        session =  new SessionFactory("org.neo4j.ogm.domain.mappings").openSession(neo4jRule.url());
    }

    /**
     * @see DATAGRAPH-715
     */
    @Test
    public void shouldCreateARichRelation()
    {
        Person person = new Person();
        session.save(person);

        Article article1 = new Article();
        session.save(article1);
        Article article2 = new Article();
        session.save(article2);

        RichRelation relation1 = new RichRelation();
        person.addRelation(article1, relation1);
        session.save(person, 1);
        session.clear();

        RichRelation relation2 = new RichRelation();
        person.addRelation(article2, relation2);
        session.save(person, 1);
    }

    @Test
    public void shouldUpdateEndNodeEntityWithoutException()
    {
        Person person = new Person();
        session.save(person);

        Article article1 = new Article();
        Tag tag1 = new Tag("tag1");
        article1.tags = Arrays.asList(tag1);
        session.save(article1);
        RichRelation relation1 = new RichRelation();
        person.addRelation(article1, relation1);
        session.save(person, 1);

        Article updateArticle = session.load(Article.class, article1.getNodeId(), 1);
        assertSame(updateArticle, ((RichRelation)updateArticle.relations.toArray()[0]).article);
        updateArticle.tags = Arrays.asList(new Tag("tag2"));
        session.save(updateArticle, 1);

        updateArticle = session.load(Article.class, article1.getNodeId(), 1);
//        assertSame(updateArticle, ((RichRelation)updateArticle.relations.toArray()[0]).article);
        session.save(updateArticle, 1);
    }
}
