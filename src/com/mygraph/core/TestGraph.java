package com.mygraph.core;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGraph {

	private Graph<Vertex> g, h;
	private Vertex v1, v2, v3, v4;

	@Before
	public void setUp() throws Exception {
		v1 = new Vertex(1);
		v2 = new Vertex(2);
		v3 = new Vertex(3);
		v4 = new Vertex(4);
	}

	int i = 666;

	@Test
	public void testIsDirected() {
		g = new Graph<Vertex>(false);
		h = new Graph<Vertex>(true);
		Assert.assertFalse(g.isDirected());
		Assert.assertTrue(h.isDirected());
		g = h = null;
	}

	@Test
	public void testCountVertices() {
		g = new Graph<Vertex>(false);
		Assert.assertEquals(g.countVertices(), 0);
		g.addEdge(v1, v2);
		Assert.assertEquals(g.countVertices(), 2);
		g.addEdge(v1, v3);
		Assert.assertEquals(g.countVertices(), 3);
		g.addEdge(v2, v4);
		Assert.assertEquals(g.countVertices(), 4);
		g.addEdge(v1, v4);
		Assert.assertEquals(g.countVertices(), 4);

		g.removeEdge(v1, v4);
		g.removeEdge(v2, v4);
		g.removeEdge(v1, v3);
		g.removeEdge(v1, v2);
		Assert.assertEquals(g.countVertices(), 4);
	}

	@Test
	public void testCountEdgesUndirected() {
		g = new Graph<Vertex>(false);
		Assert.assertEquals(g.countEdges(), 0);
		g.addEdge(v1, v2);
		Assert.assertEquals(g.countEdges(), 1);
		g.addEdge(v1, v3);
		Assert.assertEquals(g.countEdges(), 2);
		g.addEdge(v2, v4);
		Assert.assertEquals(g.countEdges(), 3);
		g.addEdge(v1, v4);
		Assert.assertEquals(g.countEdges(), 4);

		g.removeEdge(v1, v4);
		g.removeEdge(v2, v4);
		g.removeEdge(v1, v3);
		g.removeEdge(v1, v2);
		Assert.assertEquals(g.countEdges(), 0);
	}

	@Test
	public void testCountEdgesDirected() {
		g = new Graph<Vertex>(true);
		Assert.assertEquals(g.countEdges(), 0);
		g.addEdge(v1, v2);
		Assert.assertEquals(g.countEdges(), 1);
		g.addEdge(v1, v3);
		Assert.assertEquals(g.countEdges(), 2);
		g.addEdge(v2, v4);
		Assert.assertEquals(g.countEdges(), 3);
		g.addEdge(v1, v4);
		Assert.assertEquals(g.countEdges(), 4);

		g.removeEdge(v1, v4);
		g.removeEdge(v2, v4);
		g.removeEdge(v1, v3);
		g.removeEdge(v1, v2);
		Assert.assertEquals(g.countEdges(), 0);
	}

	@Test
	public void testAddEdgeUndirected() {
		g = new Graph<Vertex>(false);

		Assert.assertFalse(g.addEdge(v1, null));
		Assert.assertFalse(g.addEdge(v1, null, 1));
		Assert.assertFalse(g.addEdge(null, v1));
		Assert.assertFalse(g.addEdge(null, v1, 1));
		Assert.assertFalse(g.addEdge(v1, v2, 0));

		Assert.assertTrue(g.addEdge(v1, v2));
		Assert.assertFalse(g.addEdge(v1, v2));
		Assert.assertFalse(g.addEdge(v1, v2, 1));
		Assert.assertFalse(g.addEdge(v1, v2, 2));

		Assert.assertTrue(g.addEdge(v1, v3));
		Assert.assertFalse(g.addEdge(v3, v1));
		Assert.assertFalse(g.addEdge(v3, v1, 1));
		Assert.assertFalse(g.addEdge(v3, v1, 2));

		Assert.assertTrue(g.addEdge(v2, v4));

		g = null;
	}

	@Test
	public void testAddEdgeDirected() {
		g = new Graph<Vertex>(true);

		Assert.assertFalse(g.addEdge(v1, null));
		Assert.assertFalse(g.addEdge(v1, null, 1));
		Assert.assertFalse(g.addEdge(null, v1));
		Assert.assertFalse(g.addEdge(null, v1, 1));
		Assert.assertFalse(g.addEdge(v1, v2, 0));

		Assert.assertTrue(g.addEdge(v1, v2));
		Assert.assertFalse(g.addEdge(v1, v2));
		Assert.assertFalse(g.addEdge(v1, v2, 1));
		Assert.assertFalse(g.addEdge(v1, v2, 2));

		Assert.assertTrue(g.addEdge(v1, v3));
		Assert.assertTrue(g.addEdge(v3, v1));
		Assert.assertFalse(g.addEdge(v3, v1, 1));
		Assert.assertFalse(g.addEdge(v3, v1, 2));

		Assert.assertTrue(g.addEdge(v2, v4));

		g = null;
	}

	@Test
	public void testRemoveEdgeUndirected() {
		g = new Graph<Vertex>(false);

		Assert.assertFalse(g.removeEdge(v1, null));
		Assert.assertFalse(g.removeEdge(null, v1));

		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		g.addEdge(v2, v4);

		Assert.assertFalse(g.removeEdge(v1, v4));
		Assert.assertFalse(g.removeEdge(v2, v3));

		Assert.assertTrue(g.removeEdge(v1, v2));
		Assert.assertFalse(g.removeEdge(v1, v2));
		Assert.assertFalse(g.removeEdge(v2, v1));

		Assert.assertTrue(g.removeEdge(v3, v1));
		Assert.assertTrue(g.removeEdge(v4, v2));

		g = null;
	}

	@Test
	public void testRemoveEdgeDirected() {
		g = new Graph<Vertex>(true);

		Assert.assertFalse(g.removeEdge(v1, null));
		Assert.assertFalse(g.removeEdge(null, v1));

		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		g.addEdge(v3, v1);
		g.addEdge(v2, v4);
		g.addEdge(v4, v2);

		Assert.assertFalse(g.removeEdge(v1, v4));
		Assert.assertFalse(g.removeEdge(v2, v3));

		Assert.assertTrue(g.removeEdge(v1, v2));
		Assert.assertFalse(g.removeEdge(v1, v2));
		Assert.assertFalse(g.removeEdge(v2, v1));

		Assert.assertTrue(g.removeEdge(v1, v3));
		Assert.assertTrue(g.removeEdge(v3, v1));
		Assert.assertTrue(g.removeEdge(v2, v4));
		Assert.assertTrue(g.removeEdge(v4, v2));

		g = null;
	}

	@Test
	public void testHasEdgeUndirected() {
		g = new Graph<Vertex>(false);

		Assert.assertFalse(g.hasEdge(v1, null));
		Assert.assertFalse(g.hasEdge(null, v1));
		
		Assert.assertFalse(g.hasEdge(v1, v2, 0));
		Assert.assertFalse(g.hasEdge(null, v2, 1));
		Assert.assertFalse(g.hasEdge(v1, null, 1));

		g.addEdge(v1, v2);
		g.addEdge(v1, v3, 2);
		g.addEdge(v2, v4);

		Assert.assertFalse(g.hasEdge(v1, v4));
		Assert.assertFalse(g.hasEdge(v2, v3));

		Assert.assertTrue(g.hasEdge(v1, v2));
		Assert.assertTrue(g.hasEdge(v1, v2, 1));
		Assert.assertTrue(g.hasEdge(v2, v1));
		Assert.assertTrue(g.hasEdge(v2, v1, 1));

		Assert.assertTrue(g.hasEdge(v1, v3));
		Assert.assertTrue(g.hasEdge(v1, v3, 2));
		Assert.assertFalse(g.hasEdge(v1, v3, 0));
		Assert.assertFalse(g.hasEdge(v1, v3, 5));

		Assert.assertTrue(g.hasEdge(v3, v1));
		Assert.assertTrue(g.hasEdge(v2, v4));
		Assert.assertTrue(g.hasEdge(v4, v2));

		g.removeEdge(v3, v1);
		Assert.assertFalse(g.hasEdge(v1, v3));
		Assert.assertFalse(g.hasEdge(v1, v3, 2));
		Assert.assertFalse(g.hasEdge(v1, v3, 0));
		Assert.assertFalse(g.hasEdge(v1, v3, 3));
		Assert.assertFalse(g.hasEdge(v3, v1));

		g = null;
	}

	@Test
	public void testHasEdgeDirected() {
		g = new Graph<Vertex>(true);

		Assert.assertFalse(g.hasEdge(v1, null));
		Assert.assertFalse(g.hasEdge(null, v1));

		g.addEdge(v1, v2);
		g.addEdge(v1, v3, 2);
		g.addEdge(v3, v1);
		g.addEdge(v2, v4);
		g.addEdge(v4, v2);

		Assert.assertFalse(g.hasEdge(v1, v4));
		Assert.assertFalse(g.hasEdge(v2, v3));

		Assert.assertTrue(g.hasEdge(v1, v2));
		Assert.assertFalse(g.hasEdge(v2, v1));
		Assert.assertTrue(g.hasEdge(v1, v3));
		Assert.assertTrue(g.hasEdge(v1, v3, 2));
		Assert.assertTrue(g.hasEdge(v3, v1));
		Assert.assertTrue(g.hasEdge(v3, v1, 1));
		Assert.assertFalse(g.hasEdge(v3, v1, 2));

		Assert.assertTrue(g.hasEdge(v2, v4));
		Assert.assertTrue(g.hasEdge(v4, v2));

		g.removeEdge(v3, v1);
		Assert.assertFalse(g.hasEdge(v3, v1));
		Assert.assertTrue(g.hasEdge(v1, v3));

		g = null;
	}

	@Test
	public void testGetNeighborsUndirected() {
		g = new Graph<Vertex>(false);

		g.addEdge(v1, v2);
		g.addEdge(v1, v3, 2);
		g.addEdge(v2, v4);
		g.addEdge(v3, v4);

		Vertex v5 = new Vertex(5);

		Assert.assertEquals(g.getNeighbors(null), null);

		Set<Vertex> neighbors = g.getNeighbors(v1);
		Assert.assertTrue(neighbors.contains(v2));
		Assert.assertTrue(neighbors.contains(v3));
		Assert.assertFalse(neighbors.contains(v4));
		Assert.assertFalse(neighbors.contains(v5));

		neighbors = g.getNeighbors(v2);
		Assert.assertTrue(neighbors.contains(v1));
		Assert.assertTrue(neighbors.contains(v4));
		Assert.assertFalse(neighbors.contains(v3));
		Assert.assertFalse(neighbors.contains(v5));

		neighbors = g.getNeighbors(v3);
		Assert.assertTrue(neighbors.contains(v1));
		Assert.assertTrue(neighbors.contains(v4));
		Assert.assertFalse(neighbors.contains(v2));
		Assert.assertFalse(neighbors.contains(v5));

		neighbors = g.getNeighbors(v4);
		Assert.assertTrue(neighbors.contains(v2));
		Assert.assertTrue(neighbors.contains(v3));
		Assert.assertFalse(neighbors.contains(v1));
		Assert.assertFalse(neighbors.contains(v5));

		g = null;
	}

	@Test
	public void testGetNeighborsDirected() {
		g = new Graph<Vertex>(true);

		g.addEdge(v1, v2);
		g.addEdge(v1, v3, 2);
		g.addEdge(v3, v1);
		g.addEdge(v2, v4);
		g.addEdge(v4, v2);

		Set<Vertex> neighbors = g.getNeighbors(v1);
		Assert.assertTrue(neighbors.contains(v2));
		Assert.assertTrue(neighbors.contains(v3));
		Assert.assertFalse(neighbors.contains(v4));

		neighbors = g.getNeighbors(v2);
		Assert.assertFalse(neighbors.contains(v1));
		Assert.assertFalse(neighbors.contains(v3));
		Assert.assertTrue(neighbors.contains(v4));

		neighbors = g.getNeighbors(v3);
		Assert.assertTrue(neighbors.contains(v1));
		Assert.assertFalse(neighbors.contains(v2));
		Assert.assertFalse(neighbors.contains(v4));

		neighbors = g.getNeighbors(v4);
		Assert.assertFalse(neighbors.contains(v1));
		Assert.assertTrue(neighbors.contains(v2));
		Assert.assertFalse(neighbors.contains(v3));

		g = null;
	}

	@Test
	public void testIsWeight() {
		g = new Graph<Vertex>(true);

		g.addEdge(v1, v2);
		g.addEdge(v1, v3, 2);
		g.addEdge(v3, v1);

		Assert.assertEquals(g.isWeight(v1, null), 0);
		Assert.assertEquals(g.isWeight(null, v2), 0);

		Assert.assertEquals(g.isWeight(v1, v2), 1);
		Assert.assertEquals(g.isWeight(v2, v1), 0);

		Assert.assertEquals(g.isWeight(v1, v3), 2);
		Assert.assertEquals(g.isWeight(v1, v4), 0);
	}

	@Test
	public void testDisplay() {
		g = new Graph<Vertex>(true);

		g.addEdge(v1, v2);
		g.addEdge(v1, v3, 2);
		g.addEdge(v3, v1);
		
		g.display();
	}
}
