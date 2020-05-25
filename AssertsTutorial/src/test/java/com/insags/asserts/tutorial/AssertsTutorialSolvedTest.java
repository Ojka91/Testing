package com.insags.asserts.tutorial;

import org.junit.Test;

import org.junit.Assert;

public class AssertsTutorialSolvedTest {

	@Test
	public void assertAsserTrueTest() {
		String expected = "Expected";
		String actual = "Expected";

		boolean condition = expected.equals(actual);

		Assert.assertTrue(condition);
	}

	@Test
	public void assertAssertFalseTest() {
		String expected = "Expected";
		String actual = "Actual";

		boolean condition = expected.equals(actual);

		Assert.assertFalse(condition);
	}

	@Test
	public void assertNullTest() {
		String expected = null;

		Assert.assertNull(expected);
	}

	@Test
	public void assertNotNullTest() {
		String expected = "Expected";

		Assert.assertNotNull(expected);
	}

	@Test
	public void assertEqualsTest() {
		String expected = "Expected";
		String actual = "Expected";

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void assertNotEqualsTest() {
		String unexpected = "Unexpected";
		String actual = "Actual";

		Assert.assertNotEquals(unexpected, actual);
	}

	@Test
	public void assertArrayEqualsTest() {
		String[] expecteds = { "uno", "dos", "tres" };
		String[] actuals = { "uno", "dos", "tres" };

		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void assertSameTest() {
		Object expected = new Object();
		Object actual = expected;

		Assert.assertSame(expected, actual);
	}

	@Test
	public void assertNotSameTest() {
		Object unexpected = new Object();
		Object actual = new Object();

		Assert.assertNotSame(unexpected, actual);
	}

	@Test
	public void assertSameStringTest() {
		String expected = "Expected";
		String actual = "Expected";

		Assert.assertSame(expected, actual);
	}

	@Test
	public void assertNotSameStringTest() {
		String unexpected = "Unexpected";
		String actual = "Actual";

		Assert.assertNotSame(unexpected, actual);
	}
}