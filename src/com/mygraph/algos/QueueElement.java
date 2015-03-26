package com.mygraph.algos;

import com.mygraph.core.Vertex;

class QueueElement<T extends Vertex> {
	private final T vertex;
	private int distance;

	QueueElement(T vertex, int distance) {
		this.vertex = vertex;
		this.distance = distance;
	}

	T getVertex() {
		return vertex;
	}

	int getDistance() {
		return distance;
	}

	void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "(" + vertex.getId() + "," + distance + ")";
	}

}