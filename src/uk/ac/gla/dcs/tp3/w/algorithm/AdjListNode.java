package uk.ac.gla.dcs.tp3.w.algorithm;

// TODO modify class to fully implement the flow and capacity data members.
public class AdjListNode {

	int capacity;
	int flow;
	Vertex v;

	public AdjListNode() {
		this(0, null);
	}

	public AdjListNode(int n) {
		this(n, null);
	}

	public AdjListNode(int w, Vertex v) {
		capacity = w;
		this.v = v;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int c) {
		capacity = c;
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
