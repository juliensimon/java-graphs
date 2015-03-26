package com.mygraph.algos;

import java.util.Iterator;
import java.util.PriorityQueue;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

//As per "Algorithms in a Nutshell", O'Reilly, 2009

//
//Find the minimum spanning tree
//Best case o((v+e)*log(v)), median case o((v+e)*log(v)), worst case o((v+e)*log(v))
//

//This implementation uses a PriorityQueue.

public class Prim<T extends Vertex> {
	
	private final Graph<T> g;
	private final int size;
	private final int[] weight;
	private final int[] predecessor;

	Prim(Graph<T> g) {
		this.g = g;
		size = g.countVertices();
		weight = new int[size + 1];
		predecessor = new int[size + 1];
	}
	
	public void minSpanningTree(T start) {
		// Start with infinite distances between the start vertex and all others
		for (int i = 1; i < (this.size + 1); i++) {
			weight[i] = Integer.MAX_VALUE;
			predecessor[i] = -1;
		}
		weight[start.getId()] = 0;

		// Build a list of (vertex, distance) couples
		PriorityQueue<QueueElement<T>> pq = 
				new PriorityQueue<QueueElement<T>>(new QueueElementMinFirst<T>());
		for (T v : g.getVertices()) {
			pq.add(new QueueElement<T>(v, weight[v.getId()]));
		}

		while (pq.size() != 0) {

			// Get and remove the vertex that is the closest to the start node
			// (aka the min node)
			// It's the first element of the queue
			T min = pq.poll().getVertex();
			assert(min != null);

			// For each neighbor
			for (T neighbor : g.getNeighbors(min)) {				
				// Find the neighbor in the queue
				Iterator<QueueElement<T>> i = pq.iterator();
				while (i.hasNext()) {
					QueueElement<T> e = i.next();
					if (e.getVertex().equals(neighbor)) {
						int w = g.isWeight(min, neighbor);
						// If this path is shorter, remember it
						int neighborId = neighbor.getId();
						if (w < weight[neighborId]) {
							predecessor[neighborId] = min.getId();
							weight[neighborId] = w;						
							// Update its distance to the start vertex
							e.setDistance(w);
							// Remove and add again to maintain queue order
							pq.remove(e);
							pq.add(e);
							break;
						}
					}
				}
			}
		}
		
		
	  for (int i=1; i<=this.size;i++) { System.out.println("pred["+i+"]"+"="+predecessor[i]); }
	  for (int i=1; i<=this.size;i++) { System.out.println("weight["+i+"]"+"="+weight[i]); }

	}
}
