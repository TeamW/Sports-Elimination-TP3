package uk.ac.gla.dcs.tp3.w.algorithm;

import java.util.LinkedList;

/**
 * class to represent a vertex in a graph
 */
public class Vertex {

	private LinkedList<AdjListNode> adjList;
	private int index;
	boolean visited;
	int predecessor;

	public Vertex() {
		this(0);
	}

	/**
	 * creates a new instance of Vertex
	 */
	public Vertex(int n) {
		adjList = new LinkedList<AdjListNode>();
		index = n;
		visited = false;
		predecessor = -1;
	}

	/**
	 * copy constructor
	 */
	public Vertex(Vertex v) {
		adjList = new LinkedList<AdjListNode>(v.getAdjList());
		index = v.getIndex();
		visited = v.getVisited();
		predecessor = v.getPred();
	}

	public LinkedList<AdjListNode> getAdjList() {
		return adjList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int n) {
		index = n;
	}

	public boolean getVisited() {
		return visited;
	}

	public void setVisited(boolean b) {
		visited = b;
	}

	public int getPred() {
		return predecessor;
	}

	public void setPred(int n) {
		predecessor = n;
	}

	public int vertexDegree() {
		return adjList.size();
	}

	public void setAdjList(LinkedList<AdjListNode> linkedList) {
		adjList = linkedList;
	}

}
