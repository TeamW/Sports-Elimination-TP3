import java.util.LinkedList;

/**
 * class to represent an undirected graph using adjacency lists
 */
public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices

	// possibly other fields representing properties of the graph

	/**
	 * creates a new instance of Graph with n vertices
	 */
	public Graph(int n) {
		numVertices = n;
		vertices = new Vertex[n];
		for (int i = 0; i < n; i++)
			vertices[i] = new Vertex(i);
	}

	public int size() {
		return numVertices;
	}

	public Vertex getVertex(int i) {
		return vertices[i];
	}

	public void setVertex(int i) {
		vertices[i] = new Vertex(i);
	}

	/**
	 * visit vertex v, with predecessor index p, during a depth first traversal
	 * of the graph
	 */
	private void Visit(Vertex v, int p) {
		v.setVisited(true);
		v.setPredecessor(p);
		LinkedList<AdjListNode> L = v.getAdjList();
		for (AdjListNode node : L) {
			int n = node.getVertexNumber();
			if (!vertices[n].getVisited()) {
				Visit(vertices[n], v.getIndex());
			}
		}
	}

	/**
	 * carry out a depth first search/traversal of the graph
	 */
	public void dfs() {
		for (Vertex v : vertices)
			v.setVisited(false);
		for (Vertex v : vertices)
			if (!v.getVisited())
				Visit(v, -1);
	}

	/**
	 * carry out a breadth first search/traversal of the graph
	 */
	public void bfs() {
		for (Vertex v : vertices) {
			v.setVisited(false);
		}
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		for (Vertex v : vertices) {
			if (!v.getVisited()) {
				v.setVisited(true);
				v.setPredecessor(v.getIndex());
				queue.add(v);
				while (!queue.isEmpty()) {
					Vertex u = queue.removeFirst();
					LinkedList<AdjListNode> list = u.getAdjList();
					for (AdjListNode node : list) {
						Vertex w = vertices[node.getVertexNumber()];
						if (!w.getVisited()) {
							w.setVisited(true);
							w.setPredecessor(u.getIndex());
							queue.add(w);
						}
					}
				}
			}
		}
	}
}
