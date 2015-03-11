package com.mygraph.algos;

import java.util.Random;

import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

public class GraphGenerator {

	private final Graph<Vertex> g;
	private final int vertexCount;
	private final long edgeCount;
	private final int maxWeight;
	private final Random rand;

	public GraphGenerator(int vertexCount, long edgeCount, int maxWeight,
			boolean isDirected) {
		g = new Graph<Vertex>(isDirected);
		this.vertexCount = vertexCount;
		this.edgeCount = edgeCount;
		this.maxWeight = maxWeight;
		rand = new Random(System.currentTimeMillis());
	}

	public Vertex getRandomNode() {
		Object[] vertices = g.getVertices().toArray();
		return (Vertex) (vertices[rand.nextInt(vertices.length)]);
	}

	public Graph<Vertex> build() {

		Vertex[] vertexArray = new Vertex[vertexCount + 1];
		long counter = 0;

		// Create vertexes
		for (int i = 1; i <= vertexCount; i++) {
			vertexArray[i] = new Vertex(i);
		}
		// Make sure that each vertex is part of the graph
		for (int i = 1; i <= vertexCount; i++) {
			int dst, weight;
			do {
				dst = rand.nextInt(vertexCount) + 1;
				weight = rand.nextInt(maxWeight) + 1;
			} while (dst == i);
			g.addEdge(vertexArray[i], vertexArray[dst], weight);
			counter++;
		}
		// Add as many edges as needed to reach 'edgeCount'
		while (counter != edgeCount) {
			int src = rand.nextInt(vertexCount) + 1;
			int dst = rand.nextInt(vertexCount) + 1;
			int weight = rand.nextInt(maxWeight) + 1;
			if (g.addEdge(vertexArray[src], vertexArray[dst], weight)) {
				counter++;
			}
			;
		}
		return g;
	}
}
