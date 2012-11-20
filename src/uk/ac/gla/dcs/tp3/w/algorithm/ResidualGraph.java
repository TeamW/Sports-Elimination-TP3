package uk.ac.gla.dcs.tp3.w.algorithm;

import java.util.LinkedList;

public class ResidualGraph extends Graph {

	public ResidualGraph(Graph g) {
		// Duplicate vertex array
		vertices = new Vertex[g.getV().length];
		for (int i = 0; i < vertices.length; i++)
			vertices[i] = new Vertex(g.getV()[i]);
		setSource(vertices[0]);
		setSink(vertices[vertices.length - 1]);
		// Create a fresh adjacency list representation
		for (Vertex v : vertices) {
			v.setAdjList(new LinkedList<AdjListNode>());
		}
		// Create backwards and forwards edges
		for (int i = 0; i < vertices.length; i++) {
			Vertex normal = g.getV()[i];
			Vertex v = vertices[i];
			for (AdjListNode n : normal.getAdjList()) {
				Vertex w = vertices[n.getVertex().getIndex()];
				int forward = n.getCapacity() - n.getFlow();
				int backward = n.getFlow();
				// Create forward edge
				if (forward != 0) {
					AdjListNode vToW = new AdjListNode(forward, w);
					v.getAdjList().add(vToW);
				}
				// Create backward edge
				if (backward != 0) {
					AdjListNode wToV = new AdjListNode(backward, v);
					w.getAdjList().add(wToV);
				}
			}
		}
	}

}
