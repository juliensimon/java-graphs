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
}
