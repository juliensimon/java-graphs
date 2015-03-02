package com.mygraph.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mygraph.core.Vertex;
import com.mygraph.core.VertexList;

public class TestVertexList {

	private Vertex v1,v2,v3,v4;
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
		assertEquals(vlist.countEdges(), 0);
		
		vlist.addEdge(v2,1);
		vlist.addEdge(v3,1);
		vlist.addEdge(v4,1);
		assertEquals(vlist.countEdges(), 3);
		
		vlist.removeEdge(v2);
		vlist.removeEdge(v3);
		vlist.removeEdge(v4);
		assertEquals(vlist.countEdges(), 0);
		
		vlist = null;
	}
	
	@Test
	public void testHasEdge() {
		vlist = new VertexList<Vertex>(v1.getId());
		
		assertFalse(vlist.hasEdgeTo(null));
		assertFalse(vlist.hasEdgeTo(null,1));
		
		vlist.addEdge(v2,1);
		assertTrue (vlist.hasEdgeTo(v2));
		assertTrue(vlist.hasEdgeTo(v2,1));
		
		vlist.addEdge(v3,1);
		assertTrue (vlist.hasEdgeTo(v3));
		assertTrue(vlist.hasEdgeTo(v2,1));
		
		vlist.addEdge(v4,1);
		assertTrue (vlist.hasEdgeTo(v4));
		assertTrue(vlist.hasEdgeTo(v4,1));
		
		vlist.removeEdge(v4);
		assertFalse(vlist.hasEdgeTo(v4));
		assertFalse(vlist.hasEdgeTo(v4,1));
		
		vlist.removeEdge(v3);
		assertFalse(vlist.hasEdgeTo(v3));
		assertFalse(vlist.hasEdgeTo(v3,1));
		
		vlist.removeEdge(v2);
		assertFalse(vlist.hasEdgeTo(v2));
		assertFalse(vlist.hasEdgeTo(v2,1));
		vlist = null;
	}
	
	@Test
	public void testAddEdge() {
		vlist = new VertexList<Vertex>(v1.getId());
		assertFalse(vlist.addEdge(null,1));
		
		assertTrue(vlist.addEdge(v2,1));
		assertFalse(vlist.addEdge(v2,1));
		assertFalse(vlist.addEdge(v2,2));

		assertTrue(vlist.addEdge(v3,1));
		assertTrue(vlist.addEdge(v4,1));
		vlist = null;
	}
	
	@Test
	public void testRemoveEdge() {
		vlist = new VertexList<Vertex>(v1.getId());
		assertFalse(vlist.removeEdge(null));
		assertFalse(vlist.removeEdge(v2));

		vlist.addEdge(v2, 1);
		vlist.addEdge(v3, 1);
		vlist.addEdge(v4, 1);
		assertTrue(vlist.removeEdge(v2));
		assertFalse(vlist.removeEdge(v2));
		assertTrue(vlist.removeEdge(v3));
		assertTrue(vlist.removeEdge(v4));
		vlist = null;
	}

	@Test
	public void testIsWeight() {
		vlist = new VertexList<Vertex>(v1.getId());
		assertEquals(vlist.isWeight(null),0);
		assertEquals(vlist.isWeight(v2), 0);
	
		vlist.addEdge(v2, 1);
		vlist.addEdge(v3, 2);
		assertEquals(vlist.isWeight(v2), 1);
		assertEquals(vlist.isWeight(v3), 2);
		assertEquals(vlist.isWeight(v4), 0);
		vlist = null;

	}
}
