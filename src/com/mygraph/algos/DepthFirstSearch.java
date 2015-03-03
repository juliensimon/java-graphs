package com.mygraph.algos;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

// As per "Algorithms in a Nutshell", O'Reilly, 2009

public class DepthFirstSearch<T extends Vertex> {
	
	private Graph<T> g;
	private int 	 size;
	
	// Vertex colors
	private final static int WHITE = 0; // not visited
	private final static int GRAY  = 1; // visited, at least one neighbor not visited
	private final static int BLACK = 2; // visited, all neighbors visited
	
	private int[] discovered;
	private int[] finished;
	private int[] predecessor;
	private int[] color;
	private int counter;
	
	DepthFirstSearch(Graph<T> g) {
		this.g 	= g;
		size 	= g.countVertices();
		discovered    	= new int[size+1];
		finished    	= new int[size+1];
		predecessor 	= new int[size+1];
		color 			= new int[size+1];		
	}
	
	private int getColor(T v) {
		return color[v.getId()];
	}
	
	private void setColor(T v, int c) {
		color[v.getId()] = c;
	}
	
	private void setPredecessor(T v, T w) {
		predecessor[v.getId()] = w.getId();
	}
	
	private void dfs_visit(T v) {
		int id = v.getId();
		setColor(v, GRAY);
		counter++;
		discovered[id] = counter;
		
		for (T w : g.getNeighbors(v)) {
			if (getColor(w) == WHITE) {
				setPredecessor(w, v);
				dfs_visit(w);
			}
		}
		setColor(v, BLACK);
		
		counter++;
		finished[id] = counter;
	}

	public void search(T start) {
		for (int i=1;i<this.size+1;i++) {
			discovered[i] = finished[i] = predecessor[i] = -1;
			color[i] = WHITE;
		}
		counter = 0;
		dfs_visit(start);
		
		for (int i=1;i<this.size+1;i++) {
			System.out.print("predecessor["+i+"]="+predecessor[i]);
			System.out.print(" discovered["+i+"]="+discovered[i]);
		}

	}
	
	public static void main(String[] args) {
		
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
	
		Graph<Vertex> g = new Graph<Vertex>(false);
		g.addEdge(v1, v2);
		g.addEdge(v1, v3, 2);
		g.addEdge(v2, v4);
		g.addEdge(v3, v4);
		
		DepthFirstSearch<Vertex> dfs = new DepthFirstSearch<Vertex>(g);
		dfs.search(v1);	
	}
}
