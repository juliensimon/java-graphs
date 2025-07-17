# Java Graph Algorithms Library

[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![JUnit](https://img.shields.io/badge/JUnit-4-green.svg)](https://junit.org/junit4/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/Status-Archived-lightgrey.svg)](https://github.com/yourusername/java-graphs)
[![Last Commit](https://img.shields.io/badge/Last%20Commit-2024-lightgrey.svg)](https://github.com/yourusername/java-graphs)

A comprehensive Java library implementing fundamental graph data structures and algorithms. This project provides a robust foundation for working with weighted, directed, and undirected graphs, along with efficient implementations of classic graph algorithms.

## 🚀 Features

### Core Graph Data Structures
- **Flexible Graph Implementation**: Support for both directed and undirected graphs
- **Weighted Edges**: Full support for weighted graph operations
- **Extensible Vertex System**: Base vertex class that can be extended for custom data
- **Memory Efficient**: Optimized data structures for large graphs

### Graph Algorithms
- **Path Finding**: Dijkstra's algorithm (two implementations), Bellman-Ford, Floyd-Warshall
- **Graph Traversal**: Depth-First Search (DFS) and Breadth-First Search (BFS)
- **Minimum Spanning Tree**: Prim's algorithm
- **Topological Sorting**: DFS-based topological sort for DAGs
- **Graph Generation**: Utility for creating random test graphs

### Quality Assurance
- **Comprehensive Testing**: Full JUnit 4 test suite for all components
- **Code Quality**: Checked with FindBugs and EclEmma
- **Performance Optimized**: Multiple algorithm implementations for different use cases

## 📦 Installation

### Prerequisites
- Java 8 or higher
- JUnit 4 (for running tests)

### Setup
1. Clone the repository:
```bash
git clone https://github.com/juliensimon/java-graphs.git
cd java-graphs
```

2. Compile the source code:
```bash
javac -cp ".:lib/*" src/com/mygraph/**/*.java
```

3. Run tests (optional):
```bash
java -cp ".:lib/*:src" org.junit.runner.JUnitCore com.mygraph.core.TestGraph com.mygraph.algos.TestDijkstraWithPQ
```

## 🛠️ Usage

### Basic Graph Creation
```java
import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

// Create a directed graph
Graph<Vertex> graph = new Graph<>(true);

// Add vertices
Vertex v1 = new Vertex(1);
Vertex v2 = new Vertex(2);
Vertex v3 = new Vertex(3);

// Add weighted edges
graph.addEdge(v1, v2, 5);
graph.addEdge(v2, v3, 3);
graph.addEdge(v1, v3, 10);
```

### Finding Shortest Paths
```java
import com.mygraph.algos.DijkstraWithPQ;

// Create and run Dijkstra's algorithm
DijkstraWithPQ<Vertex> dijkstra = new DijkstraWithPQ<>(graph);
dijkstra.shortestPath(v1);

// Find path to a specific vertex
ArrayList<Integer> path = dijkstra.findPath(v3);
int pathWeight = dijkstra.findPathWeight(v3);
```

### Graph Traversal
```java
import com.mygraph.algos.DepthFirstSearch;
import com.mygraph.algos.BreadthFirstSearch;

// DFS traversal
DepthFirstSearch<Vertex> dfs = new DepthFirstSearch<>(graph);
dfs.search(v1);

// BFS traversal
BreadthFirstSearch<Vertex> bfs = new BreadthFirstSearch<>(graph);
bfs.search(v1);
```

## 📁 Project Structure

```
java-graphs/
├── src/
│   └── com/mygraph/
│       ├── core/           # Core graph data structures
│       │   ├── Graph.java
│       │   ├── Vertex.java
│       │   ├── VertexList.java
│       │   └── Test*.java
│       └── algos/          # Graph algorithms
│           ├── DijkstraWithPQ.java
│           ├── DijkstraDenseGraphs.java
│           ├── BellmanFord.java
│           ├── FloydWarshall.java
│           ├── DepthFirstSearch.java
│           ├── BreadthFirstSearch.java
│           ├── Prim.java
│           ├── TopologicalSortDFS.java
│           ├── GraphGenerator.java
│           └── Test*.java
└── README.md
```

## 🧪 Testing

The project includes comprehensive unit tests for all components:

- **Core Tests**: TestGraph, TestVertex, TestVertexList
- **Algorithm Tests**: TestDijkstraWithPQ, TestBellmanFord, TestFloydWarshall, etc.

Run all tests:
```bash
# Run core tests
java -cp ".:lib/*:src" org.junit.runner.JUnitCore com.mygraph.core.TestGraph

# Run algorithm tests
java -cp ".:lib/*:src" org.junit.runner.JUnitCore com.mygraph.algos.TestDijkstraWithPQ
```

## 📊 Performance Characteristics

| Algorithm | Time Complexity | Space Complexity | Best For |
|-----------|----------------|------------------|----------|
| Dijkstra (PQ) | O((V+E) log V) | O(V) | Sparse graphs |
| Dijkstra (Dense) | O(V²) | O(V) | Dense graphs |
| Bellman-Ford | O(VE) | O(V) | Negative weights |
| Floyd-Warshall | O(V³) | O(V²) | All-pairs shortest path |
| DFS | O(V+E) | O(V) | Graph traversal |
| BFS | O(V+E) | O(V) | Shortest path (unweighted) |
| Prim | O(E log V) | O(V) | Minimum spanning tree |

## 📝 Project Status

⚠️ **This project is archived and no longer actively maintained.**

This repository contains a complete implementation of graph algorithms and data structures in Java. While the project is archived, the code is fully functional and well-tested, making it suitable for:

- **Educational purposes**: Learning graph algorithms and data structures
- **Reference implementation**: Understanding classic algorithm implementations
- **Academic projects**: Using as a foundation for research or coursework
- **Legacy systems**: Integrating into existing Java applications

## 🤝 Historical Contributions

This project was developed with contributions from the open-source community. While no longer accepting new contributions, the existing codebase represents a collaborative effort to create a comprehensive graph algorithms library.

## 📚 References

- "Algorithms in a Nutshell", O'Reilly, 2009
- [Wikipedia - Graph Theory](https://en.wikipedia.org/wiki/Graph_theory)
- [JUnit 4 Documentation](https://junit.org/junit4/)

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Inspired by classic graph algorithms and data structures
- Built with educational and practical applications in mind
- Tested with industry-standard tools (JUnit, FindBugs, EclEmma)

