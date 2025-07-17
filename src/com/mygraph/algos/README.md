# Graph Algorithms

[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![JUnit](https://img.shields.io/badge/JUnit-4-green.svg)](https://junit.org/junit4/)
[![Coverage](https://img.shields.io/badge/Coverage-90%25-brightgreen.svg)](https://github.com/yourusername/java-graphs)

This package contains implementations of classic graph algorithms built on top of the core graph data structures. Each algorithm is optimized for specific use cases and includes comprehensive test coverage.

## 🚀 Available Algorithms

### Path Finding Algorithms

#### DijkstraWithPQ.java
**Priority Queue Implementation** - Optimal for sparse graphs

**Features:**
- Uses Java's PriorityQueue for efficient vertex selection
- Time complexity: O((V+E) log V)
- Best for graphs with fewer edges than vertices
- Guaranteed shortest path (no negative weights)

**Usage:**
```java
import com.mygraph.algos.DijkstraWithPQ;
import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

Graph<Vertex> graph = new Graph<>(true);
// ... add vertices and edges ...

DijkstraWithPQ<Vertex> dijkstra = new DijkstraWithPQ<>(graph);
dijkstra.shortestPath(startVertex);

// Find path to destination
ArrayList<Integer> path = dijkstra.findPath(destinationVertex);
int totalWeight = dijkstra.findPathWeight(destinationVertex);
```

#### DijkstraDenseGraphs.java
**Array-based Implementation** - Optimized for dense graphs

**Features:**
- Uses arrays for vertex selection (faster for dense graphs)
- Time complexity: O(V²)
- Best for graphs with many edges
- Memory efficient for large dense graphs

**Usage:**
```java
import com.mygraph.algos.DijkstraDenseGraphs;

DijkstraDenseGraphs<Vertex> dijkstra = new DijkstraDenseGraphs<>(graph);
dijkstra.shortestPath(startVertex);
```

#### BellmanFord.java
**Negative Weight Support** - Handles negative edge weights

**Features:**
- Can detect negative cycles
- Time complexity: O(VE)
- Works with negative edge weights
- Slower but more flexible than Dijkstra

**Usage:**
```java
import com.mygraph.algos.BellmanFord;

BellmanFord<Vertex> bellman = new BellmanFord<>(graph);
bellman.shortestPath(startVertex);

// Check for negative cycles
if (bellman.hasNegativeCycle()) {
    System.out.println("Graph contains negative cycle!");
}
```

#### FloydWarshall.java
**All-Pairs Shortest Path** - Finds shortest paths between all vertex pairs

**Features:**
- Computes shortest paths between all pairs of vertices
- Time complexity: O(V³)
- Space complexity: O(V²)
- Can detect negative cycles
- Useful for small to medium graphs

**Usage:**
```java
import com.mygraph.algos.FloydWarshall;

FloydWarshall<Vertex> floyd = new FloydWarshall<>(graph);
floyd.computeShortestPaths();

// Get shortest path between any two vertices
ArrayList<Integer> path = floyd.getPath(vertex1, vertex2);
int distance = floyd.getDistance(vertex1, vertex2);
```

### Graph Traversal Algorithms

#### DepthFirstSearch.java
**DFS Implementation** - Depth-first graph traversal

**Features:**
- Time complexity: O(V+E)
- Space complexity: O(V)
- Finds paths (not necessarily shortest)
- Useful for topological sorting, cycle detection

**Usage:**
```java
import com.mygraph.algos.DepthFirstSearch;

DepthFirstSearch<Vertex> dfs = new DepthFirstSearch<>(graph);
dfs.search(startVertex);

// Get traversal order
ArrayList<Integer> traversalOrder = dfs.getTraversalOrder();
// Check if vertex is reachable
boolean reachable = dfs.isReachable(targetVertex);
```

#### BreadthFirstSearch.java
**BFS Implementation** - Breadth-first graph traversal

**Features:**
- Time complexity: O(V+E)
- Space complexity: O(V)
- Finds shortest path in unweighted graphs
- Level-by-level exploration

**Usage:**
```java
import com.mygraph.algos.BreadthFirstSearch;

BreadthFirstSearch<Vertex> bfs = new BreadthFirstSearch<>(graph);
bfs.search(startVertex);

// Get shortest path (unweighted)
ArrayList<Integer> shortestPath = bfs.getPath(targetVertex);
int distance = bfs.getDistance(targetVertex);
```

### Specialized Algorithms

#### Prim.java
**Minimum Spanning Tree** - Prim's algorithm implementation

**Features:**
- Finds minimum spanning tree for connected graphs
- Time complexity: O(E log V) with priority queue
- Works with undirected graphs
- Useful for network design, clustering

**Usage:**
```java
import com.mygraph.algos.Prim;

// Create undirected graph
Graph<Vertex> undirectedGraph = new Graph<>(false);
// ... add edges ...

Prim<Vertex> prim = new Prim<>(undirectedGraph);
prim.computeMST(startVertex);

// Get MST edges
ArrayList<Edge> mstEdges = prim.getMSTEdges();
int totalWeight = prim.getMSTWeight();
```

#### TopologicalSortDFS.java
**Topological Sorting** - DFS-based implementation

**Features:**
- Finds topological order in Directed Acyclic Graphs (DAGs)
- Time complexity: O(V+E)
- Detects cycles in directed graphs
- Useful for dependency resolution, build systems

**Usage:**
```java
import com.mygraph.algos.TopologicalSortDFS;

TopologicalSortDFS<Vertex> topo = new TopologicalSortDFS<>(graph);
ArrayList<Integer> topologicalOrder = topo.sort();

if (topologicalOrder == null) {
    System.out.println("Graph contains cycles - no topological order exists");
} else {
    System.out.println("Topological order: " + topologicalOrder);
}
```

### Utility Classes

#### GraphGenerator.java
**Random Graph Generation** - Creates test graphs

**Features:**
- Generates random graphs for testing
- Configurable density and size
- Supports both directed and undirected graphs
- Useful for algorithm benchmarking

**Usage:**
```java
import com.mygraph.algos.GraphGenerator;

// Generate random directed graph
Graph<Vertex> randomGraph = GraphGenerator.generateDirectedGraph(100, 0.3);

// Generate random undirected graph
Graph<Vertex> undirectedGraph = GraphGenerator.generateUndirectedGraph(50, 0.5);
```

#### QueueElement.java & QueueElementMinFirst.java
**Priority Queue Support** - Helper classes for Dijkstra's algorithm

**Features:**
- Encapsulates vertex-distance pairs
- Custom comparator for minimum-first ordering
- Used by DijkstraWithPQ implementation

## 🛠️ Algorithm Selection Guide

| Use Case | Recommended Algorithm | Reason |
|----------|----------------------|---------|
| Shortest path, no negative weights, sparse graph | DijkstraWithPQ | Fastest for sparse graphs |
| Shortest path, no negative weights, dense graph | DijkstraDenseGraphs | Optimized for dense graphs |
| Shortest path with negative weights | BellmanFord | Handles negative weights |
| All-pairs shortest path | FloydWarshall | Computes all paths at once |
| Graph traversal, cycle detection | DepthFirstSearch | Efficient traversal |
| Shortest path in unweighted graph | BreadthFirstSearch | Guaranteed shortest path |
| Minimum spanning tree | Prim | Efficient MST algorithm |
| Dependency resolution | TopologicalSortDFS | Handles DAGs and cycles |

## 📊 Performance Comparison

| Algorithm | Time Complexity | Space Complexity | Best For | Worst For |
|-----------|----------------|------------------|----------|-----------|
| DijkstraWithPQ | O((V+E) log V) | O(V) | Sparse graphs | Dense graphs |
| DijkstraDenseGraphs | O(V²) | O(V) | Dense graphs | Sparse graphs |
| BellmanFord | O(VE) | O(V) | Negative weights | Large graphs |
| FloydWarshall | O(V³) | O(V²) | Small graphs | Large graphs |
| DFS | O(V+E) | O(V) | Traversal | Path finding |
| BFS | O(V+E) | O(V) | Unweighted paths | Weighted graphs |
| Prim | O(E log V) | O(V) | MST | Disconnected graphs |
| TopologicalSort | O(V+E) | O(V) | DAGs | Cyclic graphs |

## 🧪 Testing

Each algorithm includes comprehensive unit tests:

- **TestDijkstraWithPQ.java**: Tests priority queue implementation
- **TestDijkstraDenseGraphs.java**: Tests array-based implementation
- **TestBellmanFord.java**: Tests negative weight handling
- **TestFloydWarshall.java**: Tests all-pairs shortest path
- **TestDepthFirstSearch.java**: Tests DFS traversal
- **TestBreadthFirstSearch.java**: Tests BFS traversal
- **TestPrim.java**: Tests minimum spanning tree
- **TestTopologicalSortDFS.java**: Tests topological sorting

Run algorithm tests:
```bash
# Compile and run all algorithm tests
javac -cp ".:lib/*" src/com/mygraph/algos/*.java
java -cp ".:lib/*:src" org.junit.runner.JUnitCore com.mygraph.algos.TestDijkstraWithPQ
```

## 🔧 Implementation Details

### Memory Management
- All algorithms use efficient data structures
- Arrays for dense operations, priority queues for sparse operations
- Automatic cleanup of temporary data structures

### Error Handling
- Null vertex checks
- Invalid graph state detection
- Cycle detection in topological sort
- Negative cycle detection in Bellman-Ford

### Performance Optimizations
- Lazy evaluation where possible
- Efficient data structure selection based on graph density
- Minimized object creation in tight loops

## 🔗 Related Components

- **[Core Package](../core/README.md)**: Graph data structures used by these algorithms
- **[Main README](../../README.md)**: Project overview and installation guide

## 📚 References

- "Algorithms in a Nutshell", O'Reilly, 2009
- [Dijkstra's Algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)
- [Bellman-Ford Algorithm](https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm)
- [Floyd-Warshall Algorithm](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm)
- [Prim's Algorithm](https://en.wikipedia.org/wiki/Prim%27s_algorithm) 