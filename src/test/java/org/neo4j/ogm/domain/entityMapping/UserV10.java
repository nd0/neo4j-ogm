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
 * One incoming and one outgoing relationship of the same type. Methods annotated, fields not annotated.
 * @author Luanne Misquitta
 */
public class UserV10 extends Entity {

	private UserV10 likes;
	private UserV10 likedBy;

	public UserV10() {
	}

	@Relationship(type = "LIKES")
	public UserV10 getLikes() {
		return likes;
	}

	@Relationship(type = "LIKES")
	public void setLikes(UserV10 likes) {
		this.likes = likes;
	}

	@Relationship(type = "LIKES", direction = "INCOMING")
	public UserV10 getLikedBy() {
		return likedBy;
	}

	@Relationship(type = "LIKES", direction = "INCOMING")
	public void setLikedBy(UserV10 likedBy) {
		this.likedBy = likedBy;
	}
}
