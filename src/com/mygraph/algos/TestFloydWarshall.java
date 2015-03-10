package com.mygraph.algos;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

public class TestFloydWarshall {

	Vertex v1, v2, v3, v4, v5, v6, v7;
	Graph<Vertex> g;
	FloydWarshall<Vertex> fw;
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
	public void test1() {
		fw = new FloydWarshall<Vertex>(g);
		fw.search();
		path = fw.findShortestPath(v1, v6);
		assertEquals(path.size(), 3);
		assertEquals(path.get(0), (Integer)1);
		assertEquals(path.get(1), (Integer)2);
		assertEquals(path.get(2), (Integer)6);
	}
	
	@Test
	public void test2() {
		fw = new FloydWarshall<Vertex>(g);
		fw.search();
		path = fw.findShortestPath(v2, v7);
		assertEquals(path.size(), 3);
		assertEquals(path.get(0), (Integer)2);
		assertEquals(path.get(1), (Integer)5);
		assertEquals(path.get(2), (Integer)7);
	}
	
	@Test
	public void test3() {
		fw = new FloydWarshall<Vertex>(g);
		fw.search();
		path = fw.findShortestPath(v7, v1);
		assertEquals(path, null);
	}
	
	@Test
	public void test4() {
		GraphGenerator gg = new GraphGenerator(100, 5000, 100, true);
		Graph<Vertex> g = gg.build();
		Vertex src = gg.getRandomNode();
		Vertex dst = gg.getRandomNode();
		
		fw = new FloydWarshall<Vertex>(g);
		fw.search();
		path = fw.findShortestPath(src, dst);		
		assertFalse(path == null);
	}

}
