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

/**
 * Getter/Setter annotated with incoming direction only. Relationship type is implied. Iterable Field annotated with direction.
 * @author Luanne Misquitta
 */
public class UserV19 {

	@Relationship(direction = "INCOMING")
	private Set<UserV19> knows;

	public UserV19() {
	}

	@Relationship(direction = "INCOMING")
	public Set<UserV19> getKnows() {
		return knows;
	}

	@Relationship(direction = "INCOMING")
	public void setKnows(Set<UserV19> friend) {
		this.knows = friend;
	}
}
