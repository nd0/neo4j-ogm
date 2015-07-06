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

package org.neo4j.ogm.domain.entityMapping.iterables;

import java.util.Set;

import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.domain.entityMapping.Entity;

/**
 * No matching setters or getters but the param type matches and the relationship direction is undirected.
 * 
 * @author Luanne Misquitta
 */
public class UserV6 extends Entity {

	private Set<UserV6> knowsPeople;

	@Relationship(type = "KNOWS", direction = "UNDIRECTED")
	public Set<UserV6> getKnowsPeople() {
		return knowsPeople;
	}

	@Relationship(type = "KNOWS", direction = "UNDIRECTED")
	public void setKnowsPeople(Set<UserV6> knowsPeople) {
		this.knowsPeople = knowsPeople;
	}
}
