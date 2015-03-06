package com.mygraph.algos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

public class TestDijkstraWithPQ {
	
	Vertex v1, v2, v3, v4, v5, v6, v7;
	Graph<Vertex> g;
	DijkstraWithPQ<Vertex> d;
	ArrayList<Integer> path;
	
	@Before
	public void setUp() throws Exception {
		v1 = new Vertex(1);
		v2 = new Vertex(2);
		v3 = new Vertex(3);
		v4 = new Vertex(4);
		v5 = new Vertex(5);
		v6 = new Vertex(6);
		v7 = new Vertex(7);

		g = new Graph<Vertex>(true);
		g.addEdge(v1, v2, 2);
		g.addEdge(v1, v3, 1);
		g.addEdge(v2, v3, 2);
		g.addEdge(v2, v5, 1);
		g.addEdge(v2, v6, 1);
		g.addEdge(v3, v4, 1);
		g.addEdge(v3, v5, 3);
		g.addEdge(v4, v7, 3);
		g.addEdge(v5, v7, 2);		
	}

	@Test
	public void test() {
		DijkstraWithPQ<Vertex> d = new DijkstraWithPQ<Vertex>(g);
		d.shortestPath(v1);
		path = d.findPath(v7);
		assertEquals(path.size(), 4);
		assertEquals(path.get(0), (Integer)1);
		assertEquals(path.get(1), (Integer)3);
		assertEquals(path.get(2), (Integer)4);
		assertEquals(path.get(3), (Integer)7);
		
		// XXX test findPathWeight()
	}

}
