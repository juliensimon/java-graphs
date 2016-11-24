package com.mygraph.core;

import java.util.HashMap;
import java.util.Set;

class VertexList<T extends Vertex> {

	private final int sourceId;
	private final HashMap<T, Integer> edges;

	VertexList(int sourceId) {
		this.sourceId = sourceId;
		this.edges = new HashMap<T, Integer>();
	}

	Set<T> getNeighbors() {
		return edges.keySet();
	}

	int countEdges() {
		return edges.size();
	}

	boolean addEdge(T destination, int weight) {
		if (destination == null) {
			return false;
		}
		if (edges.get(destination) != null) {
			return false;
		}
		edges.put(destination, weight);
		return true;
	}

	boolean removeEdge(T destination) {
		if (destination == null) {
			return false;
		}
		return (edges.remove(destination) != null);
	}

	boolean hasEdgeTo(T destination) {
		if (destination == null) {
			return false;
		}
		return (edges.get(destination) != null);
	}

	boolean hasEdgeTo(T destination, int weight) {
		if (destination == null) {
			return false;
		}
		Integer i = edges.get(destination);
		if ((i != null) && (i == weight)) {
			return true;
		} else {
			return false;
		}
	}

	int getWeight(T destination) {
		if (destination == null) {
			return 0;
		}
		Integer i = edges.get(destination);
		if (i != null) {
			return i;
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return "(" + sourceId + ")->" + edges;
	}
}
