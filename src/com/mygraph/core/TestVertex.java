package com.mygraph.core;

import org.junit.Assert;
import org.junit.Test;

public class TestVertex {

	@Test
	public void testGetId() {

		Vertex v = new Vertex(5);
		Assert.assertEquals(v.getId(), 5);
	}

}
