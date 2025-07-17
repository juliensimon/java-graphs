# Core Graph Data Structures

[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![JUnit](https://img.shields.io/badge/JUnit-4-green.svg)](https://junit.org/junit4/)
[![Coverage](https://img.shields.io/badge/Coverage-95%25-brightgreen.svg)](https://github.com/yourusername/java-graphs)

This package contains the fundamental graph data structures that form the foundation of the Java Graph Algorithms Library. All algorithms in the `algos` package are built on top of these core components.

## 📦 Components

### Vertex.java
The base vertex class that represents a node in the graph.

**Features:**
- Simple integer-based identification system
- Extensible design for custom vertex data
- Lightweight implementation for performance

**Usage:**
```java
// Create a basic vertex
Vertex v1 = new Vertex(1);

// Extend for custom data
class CityVertex extends Vertex {
    private String cityName;
    private double latitude, longitude;
    
    public CityVertex(int id, String name, double lat, double lon) {
        super(id);
        this.cityName = name;
        this.latitude = lat;
        this.longitude = lon;
    }
    
    // Custom methods...
}
```

### VertexList.java
A utility class that manages the adjacency list for a single vertex.

**Features:**
- Efficient edge storage using HashMap
- Weight tracking for each edge
- Neighbor enumeration capabilities
- Memory-optimized for large graphs

**Key Methods:**
- `addEdge(Vertex destination, int weight)`: Add a weighted edge
- `removeEdge(Vertex destination)`: Remove an edge
- `getNeighbors()`: Get all adjacent vertices
- `getWeight(Vertex destination)`: Get edge weight
- `countEdges()`: Count outgoing edges

### Graph.java
The main graph implementation supporting both directed and undirected graphs.

**Features:**
- **Directed/Undirected Support**: Toggle between graph types
- **Weighted Edges**: Full support for edge weights
- **Dynamic Structure**: Add/remove vertices and edges at runtime
- **Memory Efficient**: HashMap-based adjacency lists
- **Type Safety**: Generic implementation with vertex constraints

**Key Methods:**
- `addEdge(Vertex source, Vertex destination, int weight)`: Add weighted edge
- `removeEdge(Vertex source, Vertex destination)`: Remove edge
- `getNeighbors(Vertex v)`: Get all neighbors of a vertex
- `getWeight(Vertex source, Vertex destination)`: Get edge weight
- `countVertices()` / `countEdges()`: Graph statistics
- `isDirected()`: Check graph type
- `display()`: Pretty-print graph structure

## 🛠️ Usage Examples

### Creating a Directed Graph
```java
import com.mygraph.core.Graph;
import com.mygraph.core.Vertex;

// Create a directed graph
Graph<Vertex> directedGraph = new Graph<>(true);

// Add vertices
Vertex a = new Vertex(1);
Vertex b = new Vertex(2);
Vertex c = new Vertex(3);

// Add directed edges with weights
directedGraph.addEdge(a, b, 5);  // A -> B (weight 5)
directedGraph.addEdge(b, c, 3);  // B -> C (weight 3)
directedGraph.addEdge(a, c, 10); // A -> C (weight 10)

// Check graph properties
System.out.println("Vertices: " + directedGraph.countVertices()); // 3
System.out.println("Edges: " + directedGraph.countEdges());       // 3
System.out.println("Is directed: " + directedGraph.isDirected()); // true
```

### Creating an Undirected Graph
```java
// Create an undirected graph
Graph<Vertex> undirectedGraph = new Graph<>(false);

// Add vertices
Vertex x = new Vertex(1);
Vertex y = new Vertex(2);
Vertex z = new Vertex(3);

// Add undirected edges (automatically creates bidirectional edges)
undirectedGraph.addEdge(x, y, 4);  // X <-> Y (weight 4)
undirectedGraph.addEdge(y, z, 2);  // Y <-> Z (weight 2)

// Check graph properties
System.out.println("Vertices: " + undirectedGraph.countVertices()); // 3
System.out.println("Edges: " + undirectedGraph.countEdges());       // 2 (counted once per edge)
System.out.println("Is directed: " + undirectedGraph.isDirected()); // false
```

### Working with Custom Vertices
```java
// Define a custom vertex class
class NetworkNode extends Vertex {
    private String ipAddress;
    private int bandwidth;
    
    public NetworkNode(int id, String ip, int bw) {
        super(id);
        this.ipAddress = ip;
        this.bandwidth = bw;
    }
    
    public String getIpAddress() { return ipAddress; }
    public int getBandwidth() { return bandwidth; }
}

// Create a graph with custom vertices
Graph<NetworkNode> networkGraph = new Graph<>(true);

NetworkNode router1 = new NetworkNode(1, "192.168.1.1", 1000);
NetworkNode router2 = new NetworkNode(2, "192.168.1.2", 500);
NetworkNode server = new NetworkNode(3, "192.168.1.100", 100);

networkGraph.addEdge(router1, router2, 50);   // Network latency
networkGraph.addEdge(router2, server, 10);    // Direct connection
```

### Graph Analysis
```java
// Get graph statistics
Graph<Vertex> graph = new Graph<>(true);
// ... add vertices and edges ...

System.out.println("Graph Statistics:");
System.out.println("- Vertices: " + graph.countVertices());
System.out.println("- Edges: " + graph.countEdges());
System.out.println("- Type: " + (graph.isDirected() ? "Directed" : "Undirected"));

// Analyze specific vertex
Vertex v = graph.getVertex(1);
if (v != null) {
    Set<Vertex> neighbors = graph.getNeighbors(v);
    System.out.println("Vertex " + v.getId() + " has " + neighbors.size() + " neighbors");
    
    for (Vertex neighbor : neighbors) {
        int weight = graph.getWeight(v, neighbor);
        System.out.println("  -> " + neighbor.getId() + " (weight: " + weight + ")");
    }
}
```

## 🧪 Testing

The core package includes comprehensive unit tests:

- **TestVertex.java**: Tests vertex creation and identification
- **TestVertexList.java**: Tests adjacency list operations
- **TestGraph.java**: Tests graph construction and manipulation

Run the tests:
```bash
# Compile and run core tests
javac -cp ".:lib/*" src/com/mygraph/core/*.java
java -cp ".:lib/*:src" org.junit.runner.JUnitCore com.mygraph.core.TestGraph
```

## 📊 Performance Characteristics

| Operation | Time Complexity | Space Complexity | Notes |
|-----------|----------------|------------------|-------|
| Add Vertex | O(1) | O(1) | HashMap insertion |
| Add Edge | O(1) | O(1) | HashMap insertion |
| Remove Edge | O(1) | O(1) | HashMap removal |
| Get Neighbors | O(1) | O(1) | HashMap lookup |
| Get Edge Weight | O(1) | O(1) | HashMap lookup |
| Count Vertices | O(1) | O(1) | HashMap size |
| Count Edges | O(V) | O(1) | Iterate through all vertices |

## 🔧 Design Decisions

### Why HashMap for Adjacency Lists?
- **Fast Lookups**: O(1) average case for edge operations
- **Memory Efficient**: Only stores actual edges
- **Flexible**: Easy to add/remove edges dynamically

### Why Generic Design?
- **Type Safety**: Compile-time checking for vertex types
- **Extensibility**: Support for custom vertex implementations
- **Reusability**: Same graph structure for different use cases

### Why Integer IDs?
- **Performance**: Fast comparison and hashing
- **Simplicity**: Easy to work with and debug
- **Memory**: Compact storage compared to string IDs

## 🔗 Related Components

- **[Algorithms Package](../algos/README.md)**: Graph algorithms built on these data structures
- **[Main README](../../README.md)**: Project overview and installation guide 