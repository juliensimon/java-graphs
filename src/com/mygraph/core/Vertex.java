package com.mygraph.core;

public class Vertex {

	private int id;
	
	public Vertex(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return ""+id;
	}
	
	public boolean equals(Object o) {
		if (o==null) return false;
		Vertex v = (Vertex)o;
		return (id == v.getId());
	}
	
	public int hashCode() {
		return id;
	}
}
