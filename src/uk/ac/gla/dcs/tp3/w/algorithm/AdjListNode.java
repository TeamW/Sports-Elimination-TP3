package uk.ac.gla.dcs.tp3.w.algorithm;

/**
 * This class represents a node of the network graph.
 * 
 * The class has three instance variables representing the flow and capacity of
 * the edge, and which vertex the edge points to.
 * 
 * @author gordonreid
 * @version 1.0
 */
public class AdjListNode {

	int capacity;
	int flow;
	Vertex v;

	/**
	 * No parameter constructor. Sets capacity to be 0 and the vertex to be
	 * null.
	 */
	public AdjListNode() {
		this(0, null);
	}

	/**
	 * Standard constructor. Sets capacity to given int, and flow to be 0. Given
	 * vertex is the end point of vertex.
	 * 
	 * @param w
	 *            capacity of edge
	 * @param v
	 *            vertex at end of edge
	 */
	public AdjListNode(int w, Vertex v) {
		capacity = w;
		flow = 0;
		this.v = v;
	}

	/**
	 * Return capacity of edge
	 * 
	 * @return capacity of edge
	 */
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int c) {
		capacity = c;
	}

	public void incCapacity() {
		capacity++;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int f) {
		flow = f;
	}

	public Vertex getVertex() {
		return v;
	}

	public void setVertex(Vertex v) {
		this.v = v;
	}

}
