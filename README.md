# Graphs

<b>com.mygraph.core</b>
- <code>Vertex</code>: base class for vertices, holding nothing but an integer id. This class can be used as-is or extended to hold actual data.
- <code>VertexList\<T extends Vertex\></code>: utility class used by <code>Graph</code> to store the list of edges for a given <code>Vertex</code>.
- <code>Graph\<T extends Vertex\></code>: class implementing a weighted graph, directed or not.
- <code>TestXXX</code>: unit tests for class XXX.

<b>com.mygraph.algos</b>
- <code>GraphGenerator</code> : utility class to generate random graphs.
- <i>DepthFirstSearch\<T extends Vertex></i>: walk a graph depth-first and find a path (not guaranteed to be shortest) between any two vertices.
- <i>BreadthFirstSearch\<T extends Vertex></i>: walk a graph breadth-first and find a path (not guaranteed to be shortest) between any two  vertices.
- <i>DijkstraWithPQ\<T extends Vertex></i>: find the guaranteed shortest-path between a start vertex and any other vertex. This implementation uses a priority queue.
- <i>FloydWarshall\<T extends Vertex></i>: find the guaranteed shortest-path between any pair of vertexes
- <i>TestXXX</i>: unit tests for class XXX.


