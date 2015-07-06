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
 * Annotated getter and setter (implied outgoing), non annotated field. Relationship type different from property name
 * @author Luanne Misquitta
 */
public class UserV5 extends Entity {

	private UserV5 friend;

	public UserV5() {
	}

	@Relationship(type = "KNOWS")
	public UserV5 getFriend() {
		return friend;
	}

	@Relationship(type = "KNOWS")
	public void setFriend(UserV5 friend) {
		this.friend = friend;
	}
}
