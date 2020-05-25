package com.insags.asserts.tutorial;

import static org.junit.Assert.*;

import org.junit.Test;

public class AssertsTutorialTest {

	@Test
	public void assertAsserTrueTest() {
		String expected = "Expected";
		String actual = "Expected";
		
		assertTrue(expected.equals(actual));
	}

	@Test
	public void assertAssertFalseTest() {
		String expected = "Expected";
		String actual = "Actual";
		
		assertFalse(expected.equals(actual));
	}

	@Test
	public void assertNullTest() {
		String expected = null;
		assertNull(expected);
	}

	@Test
	public void assertNotNullTest() {
		String expected = "Expected";
		assertNotNull(expected);
	}

	@Test
	public void assertEqualsTest() {
		String expected = "Expected";
		String actual = "Expected";
		assertEquals(expected, actual);
	}

	@Test
	public void assertNotEqualsTest() {
		String unexpected = "Unexpected";
		String actual = "Actual";
		assertNotEquals(unexpected, actual);
	}

	@Test
	public void assertArrayEqualsTest() {
		String[] expecteds = { "uno", "dos", "tres" };
		String[] actuals = { "uno", "dos", "tres" };
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void assertSameTest() {
		Object expected = new Object();
		Object actual = expected;
		assertSame(expected, actual);
	}

	@Test
	public void assertNotSameTest() {
		Object unexpected = new Object();
		Object actual = new Object();
		assertNotSame(unexpected, actual);
	}

	@Test
	public void assertSameStringTest() {
		String expected = "Expected";
		String actual = "Expected";
		assertSame(expected, actual);
	}

	@Test
	public void assertNotSameStringTest() {
		String unexpected = "Unexpected";
		String actual = "Actual";
		assertNotSame(unexpected, actual);
	}
}