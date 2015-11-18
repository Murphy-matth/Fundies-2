import java.util.*;

public class graph {

}

class Vertex {
	ArrayList<Edge> outEdges; // edges from this node
	
	public ArrayList<Edge> getEdges() {
		return this.outEdges;
	}
	
	public boolean sameVertex(Vertex v) {
		/*
		 * Incomplete
		 */
		return true;
	}
}

class Edge {
	Vertex from;
	Vertex to;
	int weight;
	
	public boolean matches(Vertex v) {
		return v.sameVertex(this.to);
	}
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
	
	public ArrayList<Vertex> inEdges(Vertex v) {
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		
		
		return vertices;
	}
}
