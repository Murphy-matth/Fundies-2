import java.util.*;

public class graph {

}

class Vertex {
	ArrayList<Edge> outEdges; // edges from this node
	
	public ArrayList<Edge> getEdges() {
		return this.outEdges;
	}
}

class Edge {
	Vertex from;
	Vertex to;
	int weight;
}

class Graph {
	ArrayList<Vertex> allVertices;
	
	
	public ArrayList<Edge> allEdges() {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (Vertex v : this.allVertices) {
			edges.addAll(v.getEdges());
		}
		return edges;
	}
	
	public ArrayList<Edge> inEdges(Vertex v) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		
		return edges;
	}
}
