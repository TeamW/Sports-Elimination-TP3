package uk.ac.gla.dcs.tp3.w.algorithm;

import java.util.LinkedList;

public class ResidualGraph extends Graph {

	public ResidualGraph(Graph g) {
		// Duplicate vertex array
		vertices = g.getV().clone();
		setSource(vertices[0]);
		setSink(vertices[vertices.length - 1]);
		// Create a fresh adjacency list representation
		for (Vertex v : vertices) {
			v.setAdjList(new LinkedList<AdjListNode>());
		}
		// Create backwards and forwards edges
		for (Vertex v : g.getV()) {
			for (AdjListNode n : v.getAdjList()) {
				Vertex w = n.getVertex();
				int vIndex = v.getIndex();
				int wIndex = w.getIndex();
				// Create forward edge
				AdjListNode vToW = new AdjListNode(n.getCapacity()
						- n.getFlow(), w);
				vertices[vIndex].getAdjList().add(vToW);
				// Create backward edge
				AdjListNode wToV = new AdjListNode(n.getFlow(), v);
				vertices[wIndex].getAdjList().add(wToV);
			}
		}
	}

}
