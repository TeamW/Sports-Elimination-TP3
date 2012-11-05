package uk.ac.gla.dcs.tp3.w.algorithm;

public class Graph {

	private Vertex[] v;
	private int[][] matrix;
	private Vertex source;
	private Vertex sink;

	public Vertex[] getV() {
		return v;
	}

	public void setV(Vertex[] v) {
		this.v = v;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public int getSize() {
		return v.length;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getSink() {
		return sink;
	}

	public void setSink(Vertex sink) {
		this.sink = sink;
	}

}
