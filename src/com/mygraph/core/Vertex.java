package com.mygraph.core;

public class Vertex {

	private final int id;

	public Vertex(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "" + id;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		Vertex v = (Vertex) o;
		return (id == v.getId());
	}

	@Override
	public int hashCode() {
		return id;
	}
}
