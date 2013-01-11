package uk.ac.gla.dcs.tp3.w.algorithm;

/**
 * This class represents a node of the network graph.
 * 
 * The class has three instance variables representing the flow and capacity of
 * the edge, and which vertex the edge points to.
 * 
 * @author Team W
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
	 * Return capacity of edge.
	 * 
	 * @return capacity of edge
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Set capacity of edge.
	 * 
	 * @param c
	 *            capacity of edge
	 */
	public void setCapacity(int c) {
		capacity = c;
	}

	/**
	 * Convenience method which adds 1 to the value of the capacity.
	 */
	public void incCapacity() {
		capacity++;
	}

	/**
	 * Return flow of edge.
	 * 
	 * @return flow of edge
	 */
	public int getFlow() {
		return flow;
	}

	/**
	 * Set flow of edge.
	 * 
	 * @param f
	 *            flow of edge
	 */
	public void setFlow(int f) {
		flow = f;
	}

	/**
	 * Get vertex at the end of the flow.
	 * 
	 * @return vertex at the end of the flow
	 */
	public Vertex getVertex() {
		return v;
	}

	/**
	 * Set the vertex at the end of the flow.
	 * 
	 * @param v
	 *            vertex to be at the end of the flow
	 */
	public void setVertex(Vertex v) {
		this.v = v;
	}

}
