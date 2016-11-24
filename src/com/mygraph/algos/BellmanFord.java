package com.mygraph.algos;

import java.util.ArrayList;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

//
// Find the shortest path between a start vertex and any other
// Best case o(v*e), median case o(v*e), worst case o(v*e)
//

public class BellmanFord<T extends Vertex> {

	private final Graph<T> g;
	private final int size;
	private final int[] distance;
	private final int[] predecessor;

	BellmanFord(Graph<T> g) {
		this.g = g;
		size = g.countVertices();
		distance = new int[size + 1];
		predecessor = new int[size + 1];
	}

	public boolean shortestPath(T start) {
		// Start with infinite distances between the start vertex and all others
		for (int i = 1; i <= this.size; i++) {
			distance[i] = Integer.MAX_VALUE;
			predecessor[i] = -1;
		}
		distance[start.getId()] = 0;

		for (int i = 1; i <= this.size; i++) {
			boolean found = false;
			// For each node,
			for (T v : g.getVertices()) {
				int vId = v.getId();
				if (distance[vId] == Integer.MAX_VALUE) {
					continue;
				}

				// Go through each of its neighbors
				// and check if the distance to the start vertex is shorter
				for (T w : g.getNeighbors(v)) {
					
					int newDist = distance[vId] + g.getWeight(v, w);
					// If it is, record the new distance
					int wId = w.getId();
					if (newDist < distance[wId]) {
						if (i == this.size) {
							System.out.println("Negative cycle");
							return false;
						}
						found = true;
						distance[wId] = newDist;
						predecessor[wId] = vId;
					}
				}
			}
			// No shorter route has been found for this round, we're done
			if (!found) {
				break;
			}
		}

		/*
		 * for (int i=1; i<this.size;i++) {
		 * System.out.println("pred["+i+"]"+"="+predecessor[i]); }
		 */

		return true;
	}

	// Reused from DijkstraWithPQ.java
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

	public int findPathWeight(T destination) {
		if (destination == null) {
			return 0;
		}
		int currentId = destination.getId();
		// If the destination has no predecessor, there is no path to it
		if (predecessor[currentId] == -1) {
			return 0;
		}
		// Walk through the predecessors and add the weight of each edge
		int weight = 0;
		while (currentId != -1) {
			weight += g.getWeight(g.getVertex(predecessor[currentId]),
					g.getVertex(currentId));
			currentId = predecessor[currentId];
		}
		return weight;
	}
}
