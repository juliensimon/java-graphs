package com.mygraph.algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

//As per "Algorithms in a Nutshell", O'Reilly, 2009

class ListElement<T extends Vertex> {
	private T 		vertex;
	private int 	distance;
	
	ListElement(T vertex, int distance) {
		this.vertex 	= vertex;
		this.distance 	= distance;
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
	
	public String toString() {
		return "("+vertex.getId()+","+distance+")";
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		ListElement<T> e = (ListElement<T>)o;
		return (e.getVertex().equals(vertex) && (e.getDistance()==distance));
	}
}

class ListElementMinFirst implements Comparator<Object> {
	@Override
	public int compare(Object o1, Object o2) {
		int i1 = ((ListElement<?>)o1).getDistance();
		int i2 = ((ListElement<?>)o2).getDistance();
		if (i1 == i2) return 0;
		if (i1 > i2)  return 1;
		else return -1;
	}

}

// This is the well-known Dijsktra algorithm to find the shortest path from a single origin
// This implementation uses a sorted ArrayList, simulating a PriorityQueue.
public class DijkstraWithPQ<T extends Vertex> {
	
	private Graph<T> 	g;
	private int 	 	size;
	private int[] 		distance;
	private int[] 		predecessor;
	
	DijkstraWithPQ(Graph<T> g) {
		this.g 			= g;
		size 			= g.countVertices();
		distance    	= new int[size+1];
		predecessor 	= new int[size+1];
	}
	
	public void shortestPath(T start) {
		// Start with infinite distances between the start vertex and all others
		for (int i=1;i<this.size+1;i++) {
			distance[i] = Integer.MAX_VALUE;
			predecessor[i] = -1;
		}
		distance[start.getId()] = 0;
		
		// Build a list of (vertex, distance) couples
		ArrayList<ListElement<T>> q = new ArrayList<ListElement<T>>();
		for (T v : g.getVertices()) {
			q.add(new ListElement<T>(v, distance[v.getId()]));
		}
		Collections.sort(q, new ListElementMinFirst());
		System.out.println(q);
		
		while (q.size() != 0) {
			// Sort the list
			Collections.sort(q, new ListElementMinFirst());
			System.out.println(q);
			// Get and remove the vertex that is the closest to the start node (aka the min node)
			// It's the first element of the list
			T min = q.get(0).getVertex();
			q.remove(0);
			
			// For each neighbor
			for (T neighbor : g.getNeighbors(min)) {
				// Get its distance to the min node 
				int weight = g.isWeight(min, neighbor);
				// Compute its distance from the start node
				int newDistance = distance[min.getId()] + weight;
				// If the distance is smaller than the one currently stored, update it
				if (newDistance < distance[neighbor.getId()]) {
					for (int i=0;i<q.size();i++) {
						ListElement<T> e = q.get(i);
						if (e.getVertex().equals(neighbor)) {
							e.setDistance(newDistance);
							break;
						}
						// Remember the new distance and the predecessor
						distance[neighbor.getId()]    = newDistance;
						predecessor[neighbor.getId()] = min.getId();
					}
				}
			}
		}
		
		for (int i=1;i<this.size+1;i++) {
			System.out.print("predecessor["+i+"]="+predecessor[i]);
			System.out.println(" distance["+i+"]="+distance[i]);
		}
	}
	
public static void main(String[] args) {

		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);
		Vertex v6 = new Vertex(6);
		Vertex v7 = new Vertex(7);

		Graph<Vertex> g = new Graph<Vertex>(false);
		g.addEdge(v1, v2, 2);
		g.addEdge(v1, v3, 1);
		g.addEdge(v2, v3, 2);
		g.addEdge(v2, v5, 1);
		g.addEdge(v2, v6, 1);
		g.addEdge(v3, v4, 1);
		g.addEdge(v3, v5, 3);
		g.addEdge(v4, v7, 3);
		g.addEdge(v5, v7, 2);

		DijkstraWithPQ<Vertex> d = new DijkstraWithPQ<Vertex>(g);
		d.shortestPath(v1);	
	}

}
