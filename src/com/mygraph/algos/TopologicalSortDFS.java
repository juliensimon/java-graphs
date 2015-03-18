package com.mygraph.algos;

import java.util.ArrayList;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

public class TopologicalSortDFS<T extends Vertex> {

	private final Graph<T> g;
	private final int size;
	private final ArrayList<T> list;

	// Vertex colors
	private final static int WHITE = 0; // not visited
	private final static int GRAY = 1; // visited temporarily
	private final static int BLACK = 2; // visited permanently

	private final int[] color;

	TopologicalSortDFS(Graph<T> g) {
		this.g = g;
		size = g.countVertices();
		list = new ArrayList<T>();
		color = new int[size + 1];
	}

	private int visit(T v) {
		int id = v.getId();

		// If this vertex has only been partially visited before,
		// The graph is not a DAG and it can't be topologically sorted
		if (color[id] == TopologicalSortDFS.GRAY) {
			return -1;
		}
		// If this vertex has not been visited at all,
		if (color[id] == TopologicalSortDFS.WHITE) {
			// Mark it as temporarily visited
			color[id] = TopologicalSortDFS.GRAY;
			// Visit all its neighbors
			for (T w : g.getNeighbors(v)) {
				if (visit(w) == -1) {
					return -1;
				}
			}
			// Mark it as permanently visited : all the vertices it leads to
			// have been already visited.
			color[id] = TopologicalSortDFS.BLACK;
			// Add it as the front of the list to guarantee topological order.
			list.add(0, v);
		}
		return 0;
	}

	public ArrayList<T> sort(T start) {
		// Topological sort requires a directed graph
		if (!g.isDirected()) {
			return null;
		}
		// Mark all nodes as not visited
		for (int i = 1; i < (this.size + 1); i++) {
			color[i] = TopologicalSortDFS.WHITE;
		}

		if (visit(start) == -1) {
			return null;
		} else {
			return list;
		}
	}
}
