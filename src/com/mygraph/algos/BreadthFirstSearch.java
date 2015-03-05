package com.mygraph.algos;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

//As per "Algorithms in a Nutshell", O'Reilly, 2009

public class BreadthFirstSearch<T extends Vertex> {

	private Graph<T> g;
	private int 	 size;
	
	// Vertex colors
	private final static int WHITE = 0; // not visited
	private final static int GRAY  = 1; // visited, at least one neighbor not visited
	private final static int BLACK = 2; // visited, all neighbors visited
	
	private int[] predecessor;
	private int[] distance;
	private int[] color;
	
	BreadthFirstSearch(Graph<T> g) {
		this.g 	= g;
		size 	= g.countVertices();
		predecessor 	= new int[size+1];
		distance    	= new int[size+1];
		color 			= new int[size+1];		
	}
	
	private void init() {
		for (int i=1;i<this.size+1;i++) {
			predecessor[i] = -1;
			distance[i] = Integer.MAX_VALUE;
			color[i] = WHITE;
		}
	}
	
	public void search(T start) {
		Queue<T> q = new LinkedBlockingQueue<T>();

		init();
	
		// Start
		color[start.getId()] = GRAY;
		distance[start.getId()] = 0;
		q.add(start);
		
		while (q.size() != 0) {
			// Get the head of the queue
			T head = q.peek();

			// For each of its neighbors,
			for (T v : g.getNeighbors(head)) {
				// If they haven't been visited yet,
				if (color[v.getId()] == WHITE) {
					// Increment its distance from the start vertex
					distance[v.getId()] = distance[head.getId()] + 1;
					// Set its predecessor
					predecessor[v.getId()] = head.getId();
					// Mark it as visited
					color[v.getId()] = GRAY;
					// Add it to the queue 
					q.add(v);
				}
			}
			// Remove the head, it has been visited and all its neighbors too
			q.poll();
			color[head.getId()] = BLACK;
		}
		
		for (int i=1;i<this.size+1;i++) {
			System.out.print("predecessor["+i+"]="+predecessor[i]);
			System.out.println(" distance["+i+"]="+distance[i]);
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
		// The list contains the path from the start vertex to the destination vertex
		return path;
	}
	
	public static void main(String[] args) {
		
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);
		Vertex v6 = new Vertex(6);
		Vertex v7 = new Vertex(7);

		Graph<Vertex> g = new Graph<Vertex>(true);
		g.addEdge(v1, v2, 2);
		g.addEdge(v1, v3, 1);
		g.addEdge(v2, v3, 2);
		g.addEdge(v2, v5, 1);
		g.addEdge(v2, v6, 1);
		g.addEdge(v3, v4, 1);
		g.addEdge(v3, v5, 3);
		g.addEdge(v4, v7, 3);
		g.addEdge(v5, v7, 2);
		
		BreadthFirstSearch<Vertex> bfs = new BreadthFirstSearch<Vertex>(g);
		bfs.search(v1);	
		System.out.println(bfs.findPath(v7));
		
		bfs.search(v2);	
		System.out.println(bfs.findPath(v7));
		
		bfs.search(v7);	
		System.out.println(bfs.findPath(v1));
	}
}
