package com.mygraph.algos;

import java.util.ArrayList;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

//As per "Algorithms in a Nutshell", O'Reilly, 2009

public class FloydWarshall<T extends Vertex> {

	private final Graph<T> g;
	private final int size;
	private final long[][] distance;
	private final int[][] predecessor;

	FloydWarshall(Graph<T> g) {
		this.g = g;
		size = g.countVertices();
		distance = new long[size + 1][size + 1];
		predecessor = new int[size + 1][size + 1];
	}

	private void init() {
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				distance[i][j] = Integer.MAX_VALUE;
				predecessor[i][j] = -1;
			}
			distance[i][i] = 0;
		}

		for (T v : g.getVertices()) {
			for (T w : g.getNeighbors(v)) {
				distance[v.getId()][w.getId()] = g.isWeight(v, w);
				predecessor[v.getId()][w.getId()] = v.getId();
			}
		}
	}

	/*
	 * private void printDistance() { for (int i=1;i<=size;i++) { for (int
	 * j=1;j<=size;j++) { System.out.print(distance[i][j]+ "\t"); }
	 * System.out.println(); } }
	 */

	public ArrayList<Integer> findShortestPath(T source, T destination) {
		if ((source == null) || (destination == null)) {
			return null;
		}

		int srcId = source.getId();
		int dstId = destination.getId();
		// If the destination has no predecessor, there is no path to it
		if (predecessor[srcId][dstId] == -1) {
			return null;
		}
		// Walk through the predecessors and add them at the front of the list
		ArrayList<Integer> path = new ArrayList<Integer>();
		while (dstId != -1) {
			path.add(0, dstId);
			dstId = predecessor[srcId][dstId];
		}
		// The list contains the path from the start vertex to the destination
		// vertex
		return path;
	}

	// This method implements the Floyd-Warshall algorithm,
	// which finds the shortest path for any given pair of vertexes
	public void search() {

		init();

		for (T u : g.getVertices()) {
			for (T v : g.getVertices()) {
				for (T w : g.getVertices()) {

					// printDistance();
					long newLength = distance[v.getId()][u.getId()]
							+ distance[u.getId()][w.getId()];
					// System.out.println("\nTrying "+v+","+u+" and "+u+","+w+" : dist="+newLength);

					if (newLength < distance[v.getId()][w.getId()]) {
						// System.out.println(" --> route is shorter");
						distance[v.getId()][w.getId()] = newLength;
						predecessor[v.getId()][w.getId()] = predecessor[u
								.getId()][w.getId()];
					}
				}
			}
		}
	}
}
