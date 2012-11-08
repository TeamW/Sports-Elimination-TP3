package uk.ac.gla.dcs.tp3.w.algorithm;

import java.util.LinkedList;

public class Vertex {

	private LinkedList<AdjListNode> AdjList;
	
	public Vertex(){
		AdjList = new LinkedList<AdjListNode>();
	}
	
	public LinkedList<AdjListNode> getAdjList() {
		return AdjList;
	}

	public void setAdjList(LinkedList<AdjListNode> AdjList) {
		this.AdjList = AdjList;
	}
	
	public void add(AdjListNode a){
		AdjList.add(a);
	}
	

}
