# Graphs

<b>com.mygraph.core</b>
- <i>Vertex</i>: base class for vertices, holding nothing but an integer id. This class can be used as-is or extended to hold actual data.
- <i>VertexList</i>: utility class used by <i>Graph</i> to store the list of edges for a given Vertex.
- <i>Graph</i>: class implementing a weighted graph, directed or not.
- <i>TestXXX</i>: unit tests for class XXX.

<b>com.mygraph.algos</b>
- <i>GraphGenerator</i> : utility class to generate random graphs.
- <i>DepthFirstSearch</i>: walk a graph depth-first and find a path (not guaranteed to be shortest) between any two vertices.
- <i>BreadthFirstSearch</i>: walk a graph breadth-first and find a path (not guaranteed to be shortest) between any two  vertices.
- <i>DijkstraWithPQ</i>: find the guaranteed shortest-path between a start vertex and any other vertex. This implementation uses a priority queue.
- <i>FloydWarshall</i>: find the guaranteed shortest-path between any pair of vertexes
- <i>TestXXX</i>: unit tests for class XXX.


