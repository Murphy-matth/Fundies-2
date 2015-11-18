import java.util.*;

public class graph {

}


/*
 * To get all of the inEdges
 * Look at all of the vertices that are not this one
 * If this vertex is contained in their outEdges
 * Than it is an inEdge
 */

class Vertex {
	String name;
	ArrayList<Edge> outEdges; // edges from this node
	
	Vertex(String name, ArrayList<Edge> outEdges) {
		this.name = name;
		this.outEdges = outEdges;
	}
	Vertex(String name) {
		this.name = name;
		this.outEdges = new ArrayList<Edge>();
	}
	
	public ArrayList<Edge> getEdges() {
		return this.outEdges;
	}
	
	public boolean sameVertex(Vertex v) {
		/*
		 * Incomplete
		 */
		return v.name == this.name;
	}
	
	public boolean contains(Vertex v) {
		for (Edge e : this.outEdges) {
			if (e.matches(v)) {
				return true;
			}
		}
		return false;
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
	
	public ArrayList<Vertex> inEdges(Vertex vtx) {
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		for (Vertex v: this.allVertices) {
			if(!v.sameVertex(vtx)) {
				if(v.contains(vtx)) {
					vertices.add(v);
				}
			}
		}
		return vertices;
	}
}
