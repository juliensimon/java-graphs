package com.mygraph.algos;


import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

public class TestDijkstraDenseGraphs {

	Vertex v1, v2, v3, v4, v5, v6, v7;
	Graph<Vertex> g;
	DijkstraDenseGraphs<Vertex> d;
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
		DijkstraDenseGraphs<Vertex> d = new DijkstraDenseGraphs<Vertex>(g);

		Assert.assertEquals(d.findPath(null), null);
		Assert.assertEquals(d.findPathWeight(null), 0);

		d.shortestPath(v1);
		path = d.findPath(v7);
		Assert.assertEquals(path.size(), 4);
		Assert.assertEquals(path.get(0), (Integer) 1);
		Assert.assertEquals(path.get(1), (Integer) 3);
		Assert.assertEquals(path.get(2), (Integer) 4);
		Assert.assertEquals(path.get(3), (Integer) 7);
		Assert.assertEquals(d.findPathWeight(v7), 5);
	}

	@Test
	public void testCycle() {
		DijkstraDenseGraphs<Vertex> d = new DijkstraDenseGraphs<Vertex>(g);

		g.addEdge(v7, v1, 1);

		d.shortestPath(v1);

		path = d.findPath(v7);
		Assert.assertEquals(path.size(), 4);
		Assert.assertEquals(path.get(0), (Integer) 1);
		Assert.assertEquals(path.get(1), (Integer) 3);
		Assert.assertEquals(path.get(2), (Integer) 4);
		Assert.assertEquals(path.get(3), (Integer) 7);
		Assert.assertEquals(d.findPathWeight(v7), 5);
		
		g.removeEdge(v7, v1);
	}
	
	@Test
	public void testLargeSparseGraph() {
		GraphGenerator gg = new GraphGenerator(1000, 5000, 1000, true);
		Graph<Vertex> g = gg.build();
		Vertex src = gg.getRandomNode();
		Vertex dst = gg.getRandomNode();

		DijkstraDenseGraphs<Vertex> d = new DijkstraDenseGraphs<Vertex>(g);
		d.shortestPath(src);
		path = d.findPath(dst);
		Assert.assertFalse(path == null);
	}
	
	@Test
	public void testLargeDenseGraph() {
		GraphGenerator gg = new GraphGenerator(1000, 900000, 1000, true);
		Graph<Vertex> g = gg.build();
		Vertex src = gg.getRandomNode();
		Vertex dst = gg.getRandomNode();

		DijkstraDenseGraphs<Vertex> d = new DijkstraDenseGraphs<Vertex>(g);
		d.shortestPath(src);
		path = d.findPath(dst);
		Assert.assertFalse(path == null);
	}
	
	@Test
	public void testVeryLargeSparseGraph() {
		GraphGenerator gg = new GraphGenerator(10000, 100000, 1000, true);
		Graph<Vertex> g = gg.build();
		Vertex src = gg.getRandomNode();
		Vertex dst = gg.getRandomNode();

		DijkstraDenseGraphs<Vertex> d = new DijkstraDenseGraphs<Vertex>(g);
		d.shortestPath(src);
		path = d.findPath(dst);
		Assert.assertFalse(path == null);
	}
	
	@Test
	public void testVeryLargeDenseGraph() {
		GraphGenerator gg = new GraphGenerator(10000, 10000000, 1000, true);
		Graph<Vertex> g = gg.build();
		Vertex src = gg.getRandomNode();
		Vertex dst = gg.getRandomNode();

		DijkstraDenseGraphs<Vertex> d = new DijkstraDenseGraphs<Vertex>(g);
		d.shortestPath(src);
		path = d.findPath(dst);
		Assert.assertFalse(path == null);
	}
}
