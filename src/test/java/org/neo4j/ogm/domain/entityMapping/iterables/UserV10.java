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
 * One incoming and one outgoing relationship of the same type. Methods annotated, iterable fields not annotated.
 * @author Luanne Misquitta
 */
public class UserV10 extends Entity{

	private Set<UserV10> likes;
	private Set<UserV10> likedBy;

	public UserV10() {
	}

	@Relationship(type = "LIKES")
	public Set<UserV10> getLikes() {
		return likes;
	}

	@Relationship(type = "LIKES")
	public void setLikes(Set<UserV10> likes) {
		this.likes = likes;
	}

	@Relationship(type = "LIKES", direction = "INCOMING")
	public Set<UserV10> getLikedBy() {
		return likedBy;
	}

	@Relationship(type = "LIKES", direction = "INCOMING")
	public void setLikedBy(Set<UserV10> likedBy) {
		this.likedBy = likedBy;
	}
}
