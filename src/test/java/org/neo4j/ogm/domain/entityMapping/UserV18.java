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

package org.neo4j.ogm.domain.entityMapping;

import org.neo4j.ogm.annotation.Relationship;

/**
 * Getter/Setter annotated with outgoing direction only. Relationship type is implied. Field not annotated.
 * @author Luanne Misquitta
 */
public class UserV18 {

	private UserV18 knows;

	public UserV18() {
	}

	@Relationship(direction = "OUTGOING")
	public UserV18 getKnows() {
		return knows;
	}

	@Relationship(direction = "OUTGOING")
	public void setKnows(UserV18 friend) {
		this.knows = friend;
	}
}
