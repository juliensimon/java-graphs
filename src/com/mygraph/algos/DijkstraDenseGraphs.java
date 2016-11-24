package com.mygraph.algos;

import java.util.ArrayList;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

//As per "Algorithms in a Nutshell", O'Reilly, 2009

//
//Find the shortest path between a start vertex and any other
//This algorithm is optimized for dense graphs
//Best case o(v^2 + e), median case o(v^2 + e), worst case o(v^2 + e)
//

public class DijkstraDenseGraphs<T extends Vertex> {

	private final Graph<T> g;
	private final int size;
	private final int[] distance;
	private final int[] predecessor;
	private final boolean[] visited;

	DijkstraDenseGraphs(Graph<T> g) {
		this.g = g;
		size = g.countVertices();
		distance = new int[size + 1];
		predecessor = new int[size + 1];
		visited = new boolean[size + 1];
	}

	public void shortestPath(T start) {
		// Start with infinite distances between the start vertex and all others
		for (int i = 1; i <= this.size; i++) {
			distance[i] = Integer.MAX_VALUE;
			predecessor[i] = -1;
			visited[i] = false;
		}
		distance[start.getId()] = 0;

		while (true) {
			int minVertexId = 0;
			int minVertexDistance = Integer.MAX_VALUE;
			// Find the unvisited vertex with the smallest distance to the start
			for (int i = 1; i <= size; i++) {
				if ((visited[i] == false) && (distance[i] < minVertexDistance)) {
					minVertexDistance = distance[i];
					minVertexId = i;
				}
			}
			if (minVertexDistance == Integer.MAX_VALUE) {
				return;
			}
			visited[minVertexId] = true;
			T minVertex = g.getVertex(minVertexId);
			for (T v : g.getNeighbors(minVertex)) {
				int vId = v.getId();
				int weight = g.getWeight(minVertex, v);
				int newDistance = distance[minVertexId] + weight;
				if (newDistance < distance[vId]) {
					distance[vId] = newDistance;
					predecessor[vId] = minVertexId;
				}
			}
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
