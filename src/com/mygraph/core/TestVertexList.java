package com.mygraph.core;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestVertexList {

	private Vertex v1, v2, v3, v4;
	private VertexList<Vertex> vlist;

	@Before
	public void setUp() throws Exception {
		v1 = new Vertex(1);
		v2 = new Vertex(2);
		v3 = new Vertex(3);
		v4 = new Vertex(4);
	}

	@Test
	public void testCountEdges() {
		vlist = new VertexList<Vertex>(v1.getId());
		Assert.assertEquals(vlist.countEdges(), 0);

		vlist.addEdge(v2, 1);
		vlist.addEdge(v3, 1);
		vlist.addEdge(v4, 1);
		Assert.assertEquals(vlist.countEdges(), 3);

		vlist.removeEdge(v2);
		vlist.removeEdge(v3);
		vlist.removeEdge(v4);
		Assert.assertEquals(vlist.countEdges(), 0);

		vlist = null;
	}

	@Test
	public void testHasEdge() {
		vlist = new VertexList<Vertex>(v1.getId());

		Assert.assertFalse(vlist.hasEdgeTo(null));
		Assert.assertFalse(vlist.hasEdgeTo(null, 1));

		vlist.addEdge(v2, 1);
		Assert.assertTrue(vlist.hasEdgeTo(v2));
		Assert.assertTrue(vlist.hasEdgeTo(v2, 1));

		vlist.addEdge(v3, 1);
		Assert.assertTrue(vlist.hasEdgeTo(v3));
		Assert.assertTrue(vlist.hasEdgeTo(v2, 1));

		vlist.addEdge(v4, 1);
		Assert.assertTrue(vlist.hasEdgeTo(v4));
		Assert.assertTrue(vlist.hasEdgeTo(v4, 1));

		vlist.removeEdge(v4);
		Assert.assertFalse(vlist.hasEdgeTo(v4));
		Assert.assertFalse(vlist.hasEdgeTo(v4, 1));

		vlist.removeEdge(v3);
		Assert.assertFalse(vlist.hasEdgeTo(v3));
		Assert.assertFalse(vlist.hasEdgeTo(v3, 1));

		vlist.removeEdge(v2);
		Assert.assertFalse(vlist.hasEdgeTo(v2));
		Assert.assertFalse(vlist.hasEdgeTo(v2, 1));
		vlist = null;
	}

	@Test
	public void testAddEdge() {
		vlist = new VertexList<Vertex>(v1.getId());
		Assert.assertFalse(vlist.addEdge(null, 1));

		Assert.assertTrue(vlist.addEdge(v2, 1));
		Assert.assertFalse(vlist.addEdge(v2, 1));
		Assert.assertFalse(vlist.addEdge(v2, 2));

		Assert.assertTrue(vlist.addEdge(v3, 1));
		Assert.assertTrue(vlist.addEdge(v4, 1));
		vlist = null;
	}

	@Test
	public void testRemoveEdge() {
		vlist = new VertexList<Vertex>(v1.getId());
		Assert.assertFalse(vlist.removeEdge(null));
		Assert.assertFalse(vlist.removeEdge(v2));

		vlist.addEdge(v2, 1);
		vlist.addEdge(v3, 1);
		vlist.addEdge(v4, 1);
		Assert.assertTrue(vlist.removeEdge(v2));
		Assert.assertFalse(vlist.removeEdge(v2));
		Assert.assertTrue(vlist.removeEdge(v3));
		Assert.assertTrue(vlist.removeEdge(v4));
		vlist = null;
	}

	@Test
	public void testIsWeight() {
		vlist = new VertexList<Vertex>(v1.getId());
		Assert.assertEquals(vlist.getWeight(null), 0);
		Assert.assertEquals(vlist.getWeight(v2), 0);

		vlist.addEdge(v2, 1);
		vlist.addEdge(v3, 2);
		Assert.assertEquals(vlist.getWeight(v2), 1);
		Assert.assertEquals(vlist.getWeight(v3), 2);
		Assert.assertEquals(vlist.getWeight(v4), 0);
		vlist = null;
	}

	@Test
	public void testGetNeighbors() {
		vlist = new VertexList<Vertex>(v1.getId());
		vlist.addEdge(v2, 1);
		vlist.addEdge(v3, 2);
		Set<Vertex> neighbors = vlist.getNeighbors();
		Assert.assertTrue(neighbors.contains(v2));
		Assert.assertTrue(neighbors.contains(v3));
		Assert.assertFalse(neighbors.contains(v4));
		vlist = null;
	}
}
