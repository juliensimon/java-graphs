package com.mygraph.algos;

import java.util.ArrayList;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

// As per "Algorithms in a Nutshell", O'Reilly, 2009

//
//Find a path between a start vertex and any other (not guaranteed to be the shortest one)
//Best case o(v+e), median case o(v+e), worst case o(v+e)
//

public class DepthFirstSearch<T extends Vertex> {

	private final Graph<T> g;
	private final int size;

	// Vertex colors
	private final static int WHITE = 0; // not visited
	private final static int GRAY = 1; // visited, at least one neighbor not
	// visited
	private final static int BLACK = 2; // visited, all neighbors visited

	private final int[] discovered;
	private final int[] finished;
	private final int[] predecessor;
	private final int[] color;
	private int counter;

	DepthFirstSearch(Graph<T> g) {
		this.g = g;
		size = g.countVertices();
		discovered = new int[size + 1];
		finished = new int[size + 1];
		predecessor = new int[size + 1];
		color = new int[size + 1];
	}

	private void dfs_visit(T v) {
		int vId = v.getId();
		int id = vId;
		color[vId] = DepthFirstSearch.GRAY;
		counter++;
		discovered[id] = counter;

		for (T w : g.getNeighbors(v)) {
			int wId = w.getId();
			if (color[wId] == DepthFirstSearch.WHITE) {
				predecessor[wId] = vId;
				dfs_visit(w);
			}
		}
		color[vId] = DepthFirstSearch.BLACK;

		counter++;
		finished[id] = counter;
	}

	public void search(T start) {
		for (int i = 1; i <= this.size; i++) {
			discovered[i] = finished[i] = predecessor[i] = -1;
			color[i] = DepthFirstSearch.WHITE;
		}
		counter = 0;
		dfs_visit(start);

		/*
		 * for (int i=1;i<this.size+1;i++) {
		 * System.out.print("predecessor["+i+"]="+predecessor[i]);
		 * System.out.println(" discovered["+i+"]="+discovered[i]); }
		 */
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
		// The list contains the path from the start vertex to the destination
		// vertex
		return path;
	}
}
