/*
 * Copyright (c)  [2011-2015] "Neo Technology" / "Graph Aware Ltd."
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with separate copyright notices and license terms. Your use of the source code for these subcomponents is subject to the terms and conditions of the subcomponent's license, as noted in the LICENSE file.
 *
 *
 */

package org.neo4j.ogm.unit.mapper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.neo4j.ogm.domain.cineasts.annotated.Actor;
import org.neo4j.ogm.domain.cineasts.annotated.Nomination;
import org.neo4j.ogm.domain.cineasts.annotated.Role;
import org.neo4j.ogm.mapper.EntityCollector;

/**
 * @author Luanne Misquitta
 */
public class EntityCollectorTest {

	@Test
	public void entityShouldBeAddedToTheCorrectCollection() {
		EntityCollector entityCollector = new EntityCollector();
		Actor actor = new Actor("Tom");
		Role role1 = new Role();
		entityCollector.recordTypeRelationship(actor,role1,"ACTS_IN","OUTGOING");

		Nomination nomination1 = new Nomination();
		entityCollector.recordTypeRelationship(actor,nomination1,"NOMINATED","INCOMING");

		Role role2 = new Role();
		entityCollector.recordTypeRelationship(actor,role2,"ACTS_IN","OUTGOING");

		Actor anotherActor = new Actor("Jim");
		Role role3 = new Role();
		entityCollector.recordTypeRelationship(anotherActor, role3, "ACTS_IN","OUTGOING");

		assertTrue(entityCollector.getOwningTypes().iterator().hasNext());
		assertTrue(entityCollector.getOwningTypes().iterator().hasNext());

		Iterator<String> relIt = entityCollector.getOwningRelationshipTypes(actor).iterator();
		List<String> rels = new ArrayList<>();
		while(relIt.hasNext()) {
			rels.add(relIt.next());
		}
		assertTrue(rels.contains("ACTS_IN"));
		assertTrue(rels.contains("NOMINATED"));

		relIt = entityCollector.getOwningRelationshipTypes(anotherActor).iterator();
		assertTrue(relIt.next().equals("ACTS_IN"));

		Set<Object> objs = entityCollector.getCollectiblesForOwnerAndRelationship(actor, "ACTS_IN", "OUTGOING");
		assertEquals(2, objs.size());
		assertTrue(objs.contains(role1));
		assertTrue(objs.contains(role2));

		objs = entityCollector.getCollectiblesForOwnerAndRelationship(actor, "NOMINATED","INCOMING");
		assertEquals(1,objs.size());
		assertTrue(objs.contains(nomination1));

		assertEquals(Role.class, entityCollector.getCollectibleTypeForOwnerAndRelationship(anotherActor, "ACTS_IN","OUTGOING"));




	}



}
