# Graphs

com.mygraph.core
- Vertex: base class for vertices, holding nothing but an integer id. This class can be used as-is or extended to hold actual data.
- VertexList <T extends Vertex >: utility class used by Graph to store the list of edges for a given Vertex.
- Graph <T extends Vertex >: class implementing a weighted graph, directed or not.
- TestXXX: unit tests for class XXX.

com.mygraph.algos
- GraphGenerator : utility class to generate random graphs.
- DepthFirstSearch <T extends Vertex>: walk a graph depth-first and find a path (not guaranteed to be shortest) between a start vertex and any other vertex.
- BreadthFirstSearch <T extends Vertex>: walk a graph breadth-first and find a path (not guaranteed to be shortest) between a start vertex and any other vertex.
- DijkstraWithPQ <T extends Vertex>: find the guaranteed shortest-path between a start vertex and any other vertex. This implementation uses a priority queue.
- DijkstraDenseGraphs <T extends Vertex>: find the guaranteed shortest-path between a start vertex and any other vertex. This implementation is optimized for dense graphs.
- BellmanFord <T extends Vertex>: find the guaranteed shortest-path between a start vertex and any other vertex.
- FloydWarshall <T extends Vertex>: find the guaranteed shortest-path between any pair of vertices.
- TopologicalSortDFS <T extends Vertex>: find a topological order in a DAG (algorithm based on DFS).
- Prim <T extends Vertex>: find the minimum spanning tree (Prim's algorithm).


- TestXXX: unit tests for class XXX.

All code checked with Junit 4, Findbugs and EclEmma.

References: 
- "Algorithms in a nutshell", O'Reilly, 2009.
- Wikipedia.

