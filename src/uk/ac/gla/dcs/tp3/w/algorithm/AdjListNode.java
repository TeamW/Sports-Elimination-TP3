package uk.ac.gla.dcs.tp3.w.algorithm;

public class AdjListNode {

	int weight;
	Vertex v;

	public AdjListNode(){
		this(0, null);
	}
	
	public AdjListNode(int w, Vertex v){
		weight = w;
		this.v = v;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Vertex getVertex() {
		return v;
	}

	public void setVertex(Vertex v){
		this.v = v;
	}

}
