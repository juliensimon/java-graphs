package com.mygraph.core;

import org.junit.Assert;
import org.junit.Test;

public class TestVertex {

	@Test
	public void testGetId() {
		Vertex v = new Vertex(5);
		Assert.assertEquals(v.getId(), 5);
	}
	
	@Test
	public void testHashCode() {
		Vertex v = new Vertex(5);
		Assert.assertEquals(v.hashCode(), 5);
	}
	
	@Test
	public void testToString() {
		Vertex v = new Vertex(5);
		Assert.assertEquals(v.toString(), "5");
	}
	
	@Test
	public void testEquals() {

		Vertex v1 = new Vertex(5);
		Vertex v2 = new Vertex(5);
		Vertex v3 = new Vertex(6);

		Assert.assertFalse(v1.equals(null));
		Assert.assertFalse(v1.equals(v3));
		Assert.assertTrue(v1.equals(v2));
	}

}
