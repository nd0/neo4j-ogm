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

import java.util.Collections;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.domain.entityMapping.iterables.*;
import org.neo4j.ogm.entityaccess.*;
import org.neo4j.ogm.metadata.info.ClassInfo;
import org.neo4j.ogm.metadata.info.DomainInfo;

/**
 * @author Luanne Misquitta
 */
public class IterableRelationalReaderWriterTest {

	private DefaultEntityAccessStrategy entityAccessStrategy;
	private DomainInfo domainInfo;

	final String KNOWS = "KNOWS";
	final String LIKES = "LIKES";

	@Before
	public void setup() {
		entityAccessStrategy = new DefaultEntityAccessStrategy();
		domainInfo = new DomainInfo("org.neo4j.ogm.domain.entityMapping.iterables");
	}

	@Test
	public void testUserV1() {
		final String KNOWN_BY = "KNOWN_BY";

		ClassInfo classInfo = this.domainInfo.getClass(UserV1.class.getName());
		UserV1 instance = new UserV1();
		Set<UserV1> relatedObject = Collections.singleton(new UserV1());

		assertNull(entityAccessStrategy.getIterableReader(classInfo, UserV1.class, KNOWN_BY, Relationship.OUTGOING));
		assertNull(entityAccessStrategy.getIterableWriter(classInfo, UserV1.class, KNOWN_BY, Relationship.OUTGOING));

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV1.class, KNOWN_BY, Relationship.INCOMING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV1.class, KNOWN_BY, Relationship.INCOMING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.getKnownBy());
		assertEquals(relatedObject, relationalReader.read(instance));

	}

	@Test
	public void testUserV2() {

		ClassInfo classInfo = this.domainInfo.getClass(UserV2.class.getName());
		UserV2 instance = new UserV2();
		Set<UserV2> relatedObject = Collections.singleton(new UserV2());

		assertNull(entityAccessStrategy.getIterableReader(classInfo, UserV2.class, KNOWS, Relationship.INCOMING));
		assertNull(entityAccessStrategy.getIterableWriter(classInfo, UserV2.class, KNOWS, Relationship.INCOMING));

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV2.class,KNOWS, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV2.class,KNOWS, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof FieldReader);
		assertTrue(relationalWriter instanceof FieldWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.getKnows());
		assertEquals(relatedObject, relationalReader.read(instance));

	}

	@Test
	public void testUserV3() {

		ClassInfo classInfo = this.domainInfo.getClass(UserV3.class.getName());
		UserV3 instance = new UserV3();
		Set<UserV3> relatedObject = Collections.singleton(new UserV3());

		assertNull(entityAccessStrategy.getIterableReader(classInfo, UserV3.class,KNOWS, Relationship.INCOMING));
		assertNull(entityAccessStrategy.getIterableWriter(classInfo, UserV3.class,KNOWS, Relationship.INCOMING));

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV3.class,KNOWS, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV3.class,KNOWS, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof FieldReader);
		assertTrue(relationalWriter instanceof FieldWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.getFriend());
		assertEquals(relatedObject, relationalReader.read(instance));

	}

	@Test
	public void testUserV4() {

		ClassInfo classInfo = this.domainInfo.getClass(UserV4.class.getName());
		UserV4 instance = new UserV4();
		Set<UserV4> relatedObject = Collections.singleton(new UserV4());

		assertNull(entityAccessStrategy.getIterableReader(classInfo, UserV4.class,KNOWS, Relationship.OUTGOING));
		assertNull(entityAccessStrategy.getIterableWriter(classInfo, UserV4.class,KNOWS, Relationship.OUTGOING));

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV4.class,KNOWS, Relationship.INCOMING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV4.class,KNOWS, Relationship.INCOMING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.getFriend());
		assertEquals(relatedObject, relationalReader.read(instance));

	}

	@Test
	public void testUserV5() {

		ClassInfo classInfo = this.domainInfo.getClass(UserV5.class.getName());
		UserV5 instance = new UserV5();
		Set<UserV5> relatedObject = Collections.singleton(new UserV5());

		assertNull(entityAccessStrategy.getIterableReader(classInfo, UserV5.class, KNOWS, Relationship.INCOMING));
		assertNull(entityAccessStrategy.getIterableWriter(classInfo, UserV5.class,KNOWS, Relationship.INCOMING));

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV5.class,KNOWS, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV5.class,KNOWS, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.getFriend());
		assertEquals(relatedObject, relationalReader.read(instance));

	}

	@Test
	public void testUserV6() {

		ClassInfo classInfo = this.domainInfo.getClass(UserV6.class.getName());
		UserV6 instance = new UserV6();
		Set<UserV6> relatedObject = Collections.singleton(new UserV6());

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV6.class, KNOWS, Relationship.INCOMING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV6.class, KNOWS, Relationship.INCOMING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV6.class, KNOWS, Relationship.OUTGOING);
		relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV6.class, KNOWS, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.getKnowsPeople());
		assertEquals(relatedObject, relationalReader.read(instance));

	}

	@Test
	public void testUserV7() {

		ClassInfo classInfo = this.domainInfo.getClass(UserV7.class.getName());
		UserV7 instance = new UserV7();
		Set<UserV7> relatedObject = Collections.singleton(new UserV7());

		assertNull(entityAccessStrategy.getIterableReader(classInfo, UserV7.class,KNOWS, Relationship.INCOMING));
		assertNull(entityAccessStrategy.getIterableWriter(classInfo, UserV7.class,KNOWS, Relationship.INCOMING));

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV7.class,KNOWS, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV7.class,KNOWS, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.getKnows());
		assertEquals(relatedObject, relationalReader.read(instance));

	}

	@Test
	public void testUserV8() {

		ClassInfo classInfo = this.domainInfo.getClass(UserV8.class.getName());
		UserV8 instance = new UserV8();
		Set<UserV8> relatedObject = Collections.singleton(new UserV8());

		assertNull(entityAccessStrategy.getIterableReader(classInfo, UserV8.class, KNOWS, Relationship.OUTGOING));
		assertNull(entityAccessStrategy.getIterableWriter(classInfo, UserV8.class,KNOWS, Relationship.OUTGOING));

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV8.class,KNOWS, Relationship.INCOMING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV8.class,KNOWS, Relationship.INCOMING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.getKnows());
		assertEquals(relatedObject, relationalReader.read(instance));

	}

	@Test
	public void testUserV9() {
		ClassInfo classInfo = this.domainInfo.getClass(UserV9.class.getName());
		UserV9 instance = new UserV9();
		Set<UserV9> relatedObjectOut = Collections.singleton(new UserV9());
		Set<UserV9> relatedObjectIn = Collections.singleton(new UserV9());

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV9.class,LIKES, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV9.class,LIKES, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof FieldReader);
		assertTrue(relationalWriter instanceof FieldWriter);

		relationalWriter.write(instance, relatedObjectOut);
		assertEquals(relatedObjectOut, instance.getLikes());
		assertEquals(relatedObjectOut, relationalReader.read(instance));

		relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV9.class, LIKES, Relationship.INCOMING);
		relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV9.class,LIKES, Relationship.INCOMING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObjectIn);
		assertEquals(relatedObjectIn, instance.getLikedBy());
		assertEquals(relatedObjectIn, relationalReader.read(instance));
	}

	@Test
	public void testUserV10() {
		ClassInfo classInfo = this.domainInfo.getClass(UserV10.class.getName());
		UserV10 instance = new UserV10();
		Set<UserV10> relatedObjectOut = Collections.singleton(new UserV10());
		Set<UserV10> relatedObjectIn = Collections.singleton(new UserV10());

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV10.class,LIKES, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV10.class,LIKES, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObjectOut);
		assertEquals(relatedObjectOut, instance.getLikes());
		assertEquals(relatedObjectOut, relationalReader.read(instance));

		relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV10.class,LIKES, Relationship.INCOMING);
		relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV10.class,LIKES, Relationship.INCOMING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObjectIn);
		assertEquals(relatedObjectIn, instance.getLikedBy());
		assertEquals(relatedObjectIn, relationalReader.read(instance));
	}

	@Test
	public void testUserV11() {
		ClassInfo classInfo = this.domainInfo.getClass(UserV11.class.getName());
		UserV11 instance = new UserV11();
		Set<UserV11> relatedObjectOut = Collections.singleton(new UserV11());
		Set<UserV11> relatedObjectIn = Collections.singleton(new UserV11());

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV11.class,LIKES, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV11.class,LIKES, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof FieldReader);
		assertTrue(relationalWriter instanceof FieldWriter);

		relationalWriter.write(instance, relatedObjectOut);
		assertEquals(relatedObjectOut, instance.getFriend());
		assertEquals(relatedObjectOut, relationalReader.read(instance));

		relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV11.class,LIKES, Relationship.INCOMING);
		relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV11.class,LIKES, Relationship.INCOMING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObjectIn);
		assertEquals(relatedObjectIn, instance.getFriendOf());
		assertEquals(relatedObjectIn, relationalReader.read(instance));
	}

	@Test
	public void testUserV12() {
		ClassInfo classInfo = this.domainInfo.getClass(UserV12.class.getName());
		UserV12 instance = new UserV12();
		Set<UserV12> relatedObjectOut = Collections.singleton(new UserV12());
		Set<UserV12> relatedObjectIn = Collections.singleton(new UserV12());

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV12.class,LIKES, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV12.class,LIKES, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObjectOut);
		assertEquals(relatedObjectOut, instance.getFriend());
		assertEquals(relatedObjectOut, relationalReader.read(instance));

		relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV12.class,LIKES, Relationship.INCOMING);
		relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV12.class,LIKES, Relationship.INCOMING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObjectIn);
		assertEquals(relatedObjectIn, instance.getFriendOf());
		assertEquals(relatedObjectIn, relationalReader.read(instance));
	}

	@Test
	public void testUserV13() {

		ClassInfo classInfo = this.domainInfo.getClass(UserV13.class.getName());
		UserV13 instance = new UserV13();
		Set<UserV13> relatedObject = Collections.singleton(new UserV13());

		assertNull(entityAccessStrategy.getIterableReader(classInfo, UserV13.class, KNOWS, Relationship.INCOMING));
		assertNull(entityAccessStrategy.getIterableWriter(classInfo, UserV13.class,KNOWS, Relationship.INCOMING));

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo,UserV13.class, KNOWS, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV13.class,KNOWS, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.getKnows());
		assertEquals(relatedObject, relationalReader.read(instance));

	}

	@Test
	public void testUserV14() {

		ClassInfo classInfo = this.domainInfo.getClass(UserV14.class.getName());
		UserV14 instance = new UserV14();
		Set<UserV14> relatedObject = Collections.singleton(new UserV14());

		assertNull(entityAccessStrategy.getIterableReader(classInfo, UserV14.class, KNOWS, Relationship.INCOMING));
		assertNull(entityAccessStrategy.getIterableWriter(classInfo, UserV14.class,KNOWS, Relationship.INCOMING));

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV14.class,KNOWS, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV14.class,KNOWS, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof FieldReader);
		assertTrue(relationalWriter instanceof FieldWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.knows);
		assertEquals(relatedObject, relationalReader.read(instance));
	}

	@Test
	public void testUserV15() {
		ClassInfo classInfo = this.domainInfo.getClass(UserV15.class.getName());
		UserV15 instance = new UserV15();
		Set<UserV15> relatedObjectOut = Collections.singleton(new UserV15());
		Set<UserV15> relatedObjectIn = Collections.singleton(new UserV15());

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV15.class,KNOWS, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV15.class,KNOWS, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof FieldReader);
		assertTrue(relationalWriter instanceof FieldWriter);

		relationalWriter.write(instance, relatedObjectOut);
		assertEquals(relatedObjectOut, instance.getKnows());
		assertEquals(relatedObjectOut, relationalReader.read(instance));

		instance = new UserV15();

		relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV15.class,KNOWS, Relationship.INCOMING);
		relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV15.class,KNOWS, Relationship.INCOMING);

		assertTrue(relationalReader instanceof FieldReader);
		assertTrue(relationalWriter instanceof FieldWriter);

		relationalWriter.write(instance, relatedObjectIn);
		assertEquals(relatedObjectIn, instance.getKnows());
		assertEquals(relatedObjectIn, relationalReader.read(instance));
	}

	@Test
	public void testUserV16() {
		ClassInfo classInfo = this.domainInfo.getClass(UserV16.class.getName());
		UserV16 instance = new UserV16();
		Set<UserV16> relatedObjectOut = Collections.singleton(new UserV16());
		Set<UserV16> relatedObjectIn = Collections.singleton(new UserV16());

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV16.class,KNOWS, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV16.class,KNOWS, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObjectOut);
		assertEquals(relatedObjectOut, instance.getKnows());
		assertEquals(relatedObjectOut, relationalReader.read(instance));

		instance = new UserV16();

		relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV16.class,KNOWS, Relationship.INCOMING);
		relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV16.class,KNOWS, Relationship.INCOMING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObjectIn);
		assertEquals(relatedObjectIn, instance.getKnows());
		assertEquals(relatedObjectIn, relationalReader.read(instance));
	}

	@Test
	public void testUserV17() {
		ClassInfo classInfo = this.domainInfo.getClass(UserV17.class.getName());
		UserV17 instance = new UserV17();
		Set<UserV17> relatedObject = Collections.singleton(new UserV17());

		assertNull(entityAccessStrategy.getIterableReader(classInfo, UserV17.class,KNOWS, Relationship.OUTGOING));
		assertNull(entityAccessStrategy.getIterableWriter(classInfo, UserV17.class,KNOWS, Relationship.OUTGOING));

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV17.class,KNOWS, Relationship.INCOMING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV17.class,KNOWS, Relationship.INCOMING);

		assertTrue(relationalReader instanceof FieldReader);
		assertTrue(relationalWriter instanceof FieldWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.knows);
		assertEquals(relatedObject, relationalReader.read(instance));

	}

	@Test
	public void testUserV18() {

		ClassInfo classInfo = this.domainInfo.getClass(UserV18.class.getName());
		UserV18 instance = new UserV18();
		Set<UserV18> relatedObject = Collections.singleton(new UserV18());

		assertNull(entityAccessStrategy.getIterableReader(classInfo, UserV18.class, KNOWS, Relationship.INCOMING));
		assertNull(entityAccessStrategy.getIterableWriter(classInfo, UserV18.class, KNOWS, Relationship.INCOMING));

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo, UserV18.class, KNOWS, Relationship.OUTGOING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo, UserV18.class, KNOWS, Relationship.OUTGOING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.getKnows());
		assertEquals(relatedObject, relationalReader.read(instance));

	}

	@Test
	public void testUserV19() {

		ClassInfo classInfo = this.domainInfo.getClass(UserV19.class.getName());
		UserV19 instance = new UserV19();
		Set<UserV19> relatedObject = Collections.singleton(new UserV19());

		assertNull(entityAccessStrategy.getIterableReader(classInfo, UserV19.class, KNOWS, Relationship.OUTGOING));
		assertNull(entityAccessStrategy.getIterableWriter(classInfo, UserV19.class, KNOWS, Relationship.OUTGOING));

		RelationalReader relationalReader = entityAccessStrategy.getIterableReader(classInfo,  UserV19.class, KNOWS, Relationship.INCOMING);
		RelationalWriter relationalWriter = entityAccessStrategy.getIterableWriter(classInfo,  UserV19.class, KNOWS, Relationship.INCOMING);

		assertTrue(relationalReader instanceof MethodReader);
		assertTrue(relationalWriter instanceof MethodWriter);

		relationalWriter.write(instance, relatedObject);
		assertEquals(relatedObject, instance.getKnows());
		assertEquals(relatedObject, relationalReader.read(instance));

	}

}
