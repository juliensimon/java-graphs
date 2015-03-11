package com.mygraph.algos;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

public class TestDepthFirstSearch {

	Vertex v1, v2, v3, v4, v5, v6, v7;
	Graph<Vertex> g;
	DepthFirstSearch<Vertex> dfs;
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
		dfs = new DepthFirstSearch<Vertex>(g);
		dfs.search(v1);
		path = dfs.findPath(v6);
		Assert.assertEquals(path.size(), 3);
		Assert.assertEquals(path.get(0), (Integer) 1);
		Assert.assertEquals(path.get(1), (Integer) 2);
		Assert.assertEquals(path.get(2), (Integer) 6);
	}

	@Test
	public void test3() {
		dfs = new DepthFirstSearch<Vertex>(g);
		dfs.search(v7);
		path = dfs.findPath(v1);
		Assert.assertEquals(path, null);
	}

	@Test
	public void test4() {
		GraphGenerator gg = new GraphGenerator(1000, 900000, 1000, true);
		Graph<Vertex> g = gg.build();
		Vertex src = gg.getRandomNode();
		Vertex dst = gg.getRandomNode();

		dfs = new DepthFirstSearch<Vertex>(g);
		dfs.search(src);
		path = dfs.findPath(dst);
		Assert.assertFalse(path == null);
	}
}
