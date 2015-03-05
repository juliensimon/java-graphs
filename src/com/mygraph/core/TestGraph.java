package com.mygraph.core;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

public class TestGraph {
	
	private Graph<Vertex> g,h;
	private Vertex v1,v2,v3,v4;

	@Before
	public void setUp() throws Exception {
		v1 = new Vertex(1);
		v2 = new Vertex(2);
		v3 = new Vertex(3);
		v4 = new Vertex(4);
	}
	
	@Test
	public void testIsDirected() {
		g = new Graph<Vertex>(false);
		h = new Graph<Vertex>(true);
		assertFalse(g.isDirected());
		assertTrue(h.isDirected());
		g = h = null;
	}
	
	@Test
	public void testCountVertices() {
		g = new Graph<Vertex>(false);
		assertEquals(g.countVertices(), 0);
		g.addEdge(v1, v2);
		assertEquals(g.countVertices(), 2);
		g.addEdge(v1, v3);
		assertEquals(g.countVertices(), 3);
		g.addEdge(v2, v4);
		assertEquals(g.countVertices(), 4);
		g.addEdge(v1, v4);
		assertEquals(g.countVertices(), 4);
		
		g.removeEdge(v1, v4);
		g.removeEdge(v2, v4);
		g.removeEdge(v1, v3);
		g.removeEdge(v1, v2);
		assertEquals(g.countVertices(), 4);
	}
	
	@Test
	public void testCountEdgesUndirected() {
		g = new Graph<Vertex>(false);
		assertEquals(g.countEdges(), 0);
		g.addEdge(v1, v2);
		assertEquals(g.countEdges(), 1);
		g.addEdge(v1, v3);
		assertEquals(g.countEdges(), 2);
		g.addEdge(v2, v4);
		assertEquals(g.countEdges(), 3);
		g.addEdge(v1, v4);
		assertEquals(g.countEdges(), 4);
		
		g.removeEdge(v1, v4);
		g.removeEdge(v2, v4);
		g.removeEdge(v1, v3);
		g.removeEdge(v1, v2);
		assertEquals(g.countEdges(), 0);
	}

	@Test
	public void testCountEdgesDirected() {
		g = new Graph<Vertex>(true);
		assertEquals(g.countEdges(), 0);
		g.addEdge(v1, v2);
		assertEquals(g.countEdges(), 1);
		g.addEdge(v1, v3);
		assertEquals(g.countEdges(), 2);
		g.addEdge(v2, v4);
		assertEquals(g.countEdges(), 3);
		g.addEdge(v1, v4);
		assertEquals(g.countEdges(), 4);
		
		g.removeEdge(v1, v4);
		g.removeEdge(v2, v4);
		g.removeEdge(v1, v3);
		g.removeEdge(v1, v2);
		assertEquals(g.countEdges(), 0);
	}
	
	@Test
	public void testAddEdgeUndirected() {
		g = new Graph<Vertex>(false);
		
		assertFalse(g.addEdge(v1, null));
		assertFalse(g.addEdge(v1, null, 1));
		assertFalse(g.addEdge(null, v1));
		assertFalse(g.addEdge(null, v1, 1));
		assertFalse(g.addEdge(v1, v2, 0));
		
		assertTrue(g.addEdge(v1, v2));
		assertFalse(g.addEdge(v1, v2));
		assertFalse(g.addEdge(v1, v2, 1));
		assertFalse(g.addEdge(v1, v2, 2));
		
		assertTrue(g.addEdge(v1, v3));
		assertFalse(g.addEdge(v3, v1));
		assertFalse(g.addEdge(v3, v1, 1));
		assertFalse(g.addEdge(v3, v1, 2));

		assertTrue(g.addEdge(v2, v4));
		
		g = null;
	}
	
	@Test
	public void testAddEdgeDirected() {
		g = new Graph<Vertex>(true);
		
		assertFalse(g.addEdge(v1, null));
		assertFalse(g.addEdge(v1, null, 1));
		assertFalse(g.addEdge(null, v1));
		assertFalse(g.addEdge(null, v1, 1));
		assertFalse(g.addEdge(v1, v2, 0));
		
		assertTrue(g.addEdge(v1, v2));
		assertFalse(g.addEdge(v1, v2));
		assertFalse(g.addEdge(v1, v2, 1));
		assertFalse(g.addEdge(v1, v2, 2));
		
		assertTrue(g.addEdge(v1, v3));
		assertTrue(g.addEdge(v3, v1));
		assertFalse(g.addEdge(v3, v1, 1));
		assertFalse(g.addEdge(v3, v1, 2));

		assertTrue(g.addEdge(v2, v4));
		
		g = null;
	}
	
	@Test
	public void testRemoveEdgeUndirected() {
		g = new Graph<Vertex>(false);
		
		assertFalse(g.removeEdge(v1, null));
		assertFalse(g.removeEdge(null, v1));
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		g.addEdge(v2, v4);

		assertFalse(g.removeEdge(v1, v4));
		assertFalse(g.removeEdge(v2, v3));
		
		assertTrue(g.removeEdge(v1, v2));
		assertFalse(g.removeEdge(v1, v2));
		assertFalse(g.removeEdge(v2, v1));
		
		assertTrue(g.removeEdge(v3, v1));
		assertTrue(g.removeEdge(v4, v2));

		g = null;
	}
	
	@Test
	public void testRemoveEdgeDirected() {
		g = new Graph<Vertex>(true);
		
		assertFalse(g.removeEdge(v1, null));
		assertFalse(g.removeEdge(null, v1));
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		g.addEdge(v3, v1);
		g.addEdge(v2, v4);
		g.addEdge(v4, v2);

		assertFalse(g.removeEdge(v1, v4));
		assertFalse(g.removeEdge(v2, v3));
		
		assertTrue(g.removeEdge(v1, v2));
		assertFalse(g.removeEdge(v1, v2));
		assertFalse(g.removeEdge(v2, v1));
		
		assertTrue(g.removeEdge(v1, v3));
		assertTrue(g.removeEdge(v3, v1));
		assertTrue(g.removeEdge(v2, v4));
		assertTrue(g.removeEdge(v4, v2));

		g = null;
	}
	
	@Test
	public void testHasEdgeUndirected() {
		g = new Graph<Vertex>(false);
		
		assertFalse(g.hasEdge(v1, null));
		assertFalse(g.hasEdge(null, v1));
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v3, 2);
		g.addEdge(v2, v4);

		assertFalse(g.hasEdge(v1, v4));
		assertFalse(g.hasEdge(v2, v3));
		
		assertTrue(g.hasEdge(v1, v2));
		assertTrue(g.hasEdge(v1, v2, 1));
		assertTrue(g.hasEdge(v2, v1));
		assertTrue(g.hasEdge(v2, v1, 1));

		assertTrue(g.hasEdge(v1, v3));
		assertTrue(g.hasEdge(v1, v3, 2));
		assertFalse(g.hasEdge(v1, v3, 0));
		assertFalse(g.hasEdge(v1, v3, 5));

		assertTrue(g.hasEdge(v3, v1));
		assertTrue(g.hasEdge(v2, v4));
		assertTrue(g.hasEdge(v4, v2));
				
		g.removeEdge(v3, v1);
		assertFalse(g.hasEdge(v1, v3));
		assertFalse(g.hasEdge(v1, v3, 2));
		assertFalse(g.hasEdge(v1, v3, 0));
		assertFalse(g.hasEdge(v1, v3, 3));
		assertFalse(g.hasEdge(v3, v1));
		
		g = null;
	}
	
	@Test
	public void testHasEdgeDirected() {
		g = new Graph<Vertex>(true);
		
		assertFalse(g.hasEdge(v1, null));
		assertFalse(g.hasEdge(null, v1));
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v3, 2);
		g.addEdge(v3, v1);
		g.addEdge(v2, v4);
		g.addEdge(v4, v2);

		assertFalse(g.hasEdge(v1, v4));
		assertFalse(g.hasEdge(v2, v3));
		
		assertTrue(g.hasEdge(v1, v2));
		assertFalse(g.hasEdge(v2, v1))
		;
		assertTrue(g.hasEdge(v1, v3));
		assertTrue(g.hasEdge(v1, v3, 2));
		assertTrue(g.hasEdge(v3, v1));
		assertTrue(g.hasEdge(v3, v1, 1));
		assertFalse(g.hasEdge(v3, v1, 2));

		assertTrue(g.hasEdge(v2, v4));
		assertTrue(g.hasEdge(v4, v2));
				
		g.removeEdge(v3, v1);
		assertFalse(g.hasEdge(v3, v1));
		assertTrue(g.hasEdge(v1, v3));
		
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

		Set<Vertex> neighbors = g.getNeighbors(v1);
		assertTrue(neighbors.contains(v2));
		assertTrue(neighbors.contains(v3));
		assertFalse(neighbors.contains(v4));
		assertFalse(neighbors.contains(v5));
		
		neighbors = g.getNeighbors(v2);
		assertTrue(neighbors.contains(v1));
		assertTrue(neighbors.contains(v4));
		assertFalse(neighbors.contains(v3));
		assertFalse(neighbors.contains(v5));

		neighbors = g.getNeighbors(v3);
		assertTrue(neighbors.contains(v1));
		assertTrue(neighbors.contains(v4));
		assertFalse(neighbors.contains(v2));
		assertFalse(neighbors.contains(v5));
		
		neighbors = g.getNeighbors(v4);
		assertTrue(neighbors.contains(v2));
		assertTrue(neighbors.contains(v3));
		assertFalse(neighbors.contains(v1));
		assertFalse(neighbors.contains(v5));
		
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
		assertTrue(neighbors.contains(v2));
		assertTrue(neighbors.contains(v3));
		assertFalse(neighbors.contains(v4));
		
		neighbors = g.getNeighbors(v2);
		assertFalse(neighbors.contains(v1));
		assertFalse(neighbors.contains(v3));
		assertTrue(neighbors.contains(v4));

		neighbors = g.getNeighbors(v3);
		assertTrue(neighbors.contains(v1));
		assertFalse(neighbors.contains(v2));
		assertFalse(neighbors.contains(v4));
		
		neighbors = g.getNeighbors(v4);
		assertFalse(neighbors.contains(v1));
		assertTrue(neighbors.contains(v2));
		assertFalse(neighbors.contains(v3));
		
		g = null;
	}
	
	@Test
	public void testIsWeight() {
		g = new Graph<Vertex>(true);
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v3, 2);
		g.addEdge(v3, v1);
		
		assertEquals(g.isWeight(v1, null), 0);
		assertEquals(g.isWeight(null, v2), 0);
		
		assertEquals(g.isWeight(v1, v2), 1);
		assertEquals(g.isWeight(v2, v1), 0);
		
		assertEquals(g.isWeight(v1, v3), 2);
		assertEquals(g.isWeight(v1, v4), 0);
	}
	
}
