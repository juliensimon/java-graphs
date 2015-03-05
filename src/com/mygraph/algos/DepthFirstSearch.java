package com.mygraph.algos;

import java.util.ArrayList;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

// As per "Algorithms in a Nutshell", O'Reilly, 2009

public class DepthFirstSearch<T extends Vertex> {
	
	private Graph<T> g;
	private int 	 size;
	
	// Vertex colors
	private final static int WHITE = 0; // not visited
	private final static int GRAY  = 1; // visited, at least one neighbor not visited
	private final static int BLACK = 2; // visited, all neighbors visited
	
	private int[] discovered;
	private int[] finished;
	private int[] predecessor;
	private int[] color;
	private int counter;
	
	DepthFirstSearch(Graph<T> g) {
		this.g 	= g;
		size 	= g.countVertices();
		discovered    	= new int[size+1];
		finished    	= new int[size+1];
		predecessor 	= new int[size+1];
		color 			= new int[size+1];		
	}
	
	private void dfs_visit(T v) {
		int id = v.getId();
		color[v.getId()] = GRAY;
		counter++;
		discovered[id] = counter;
		
		for (T w : g.getNeighbors(v)) {
			if (color[w.getId()] == WHITE) {
				predecessor[w.getId()] = v.getId();
				dfs_visit(w);
			}
		}
		color[v.getId()] = BLACK;
		
		counter++;
		finished[id] = counter;
	}

	public void search(T start) {
		for (int i=1;i<this.size+1;i++) {
			discovered[i] = finished[i] = predecessor[i] = -1;
			color[i] = WHITE;
		}
		counter = 0;
		dfs_visit(start);
		
		for (int i=1;i<this.size+1;i++) {
			System.out.print("predecessor["+i+"]="+predecessor[i]);
			System.out.println(" discovered["+i+"]="+discovered[i]);
		}
	}
	
	public ArrayList<Integer> findPath(T destination) {
		if (destination == null) {
			return null;
		}
		int currentId = destination.getId();
		// If the destination has no predecessor, there is no path to it
		if (predecessor[currentId] == -1) {
			return null;
		}
		// Walk through the predecessors and add them at the front of the list
		ArrayList<Integer> path = new ArrayList<Integer>();
		while (currentId != -1) {
			path.add(0, currentId);
			currentId = predecessor[currentId];
		}
		// The list contains the path from the start vertex to the destination vertex
		return path;
	}
	
	public static void main(String[] args) {
		
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);
		Vertex v6 = new Vertex(6);
		Vertex v7 = new Vertex(7);

		Graph<Vertex> g = new Graph<Vertex>(true);
		g.addEdge(v1, v2, 2);
		g.addEdge(v1, v3, 1);
		g.addEdge(v2, v3, 2);
		g.addEdge(v2, v5, 1);
		g.addEdge(v2, v6, 1);
		g.addEdge(v3, v4, 1);
		g.addEdge(v3, v5, 3);
		g.addEdge(v4, v7, 3);
		g.addEdge(v5, v7, 2);
		
		DepthFirstSearch<Vertex> dfs = new DepthFirstSearch<Vertex>(g);
		
		dfs.search(v1);	
		System.out.println(dfs.findPath(v7));
		
		dfs.search(v2);
		System.out.println(dfs.findPath(v7));
		
		dfs.search(v7);
		System.out.println(dfs.findPath(v1));
		
	}
}
