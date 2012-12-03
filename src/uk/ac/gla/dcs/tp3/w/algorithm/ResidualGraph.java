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
			Vertex original = g.getV()[i];
			Vertex v = vertices[i];
			for (AdjListNode n : original.getAdjList()) {
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
		// Create the adjacency matrix representation of the graph.
		// Initialise every location to 0.
		matrix = new int[vertices.length][vertices.length];
		for (int i = 0; i < vertices.length; i++)
			for (int j = 0; j < vertices.length; j++)
				matrix[i][j] = 0;
		// Now set every non-zero location to the value of the capacity.
		for (Vertex v : vertices) {
			for (AdjListNode n : v.getAdjList()) {
				int loc = n.getVertex().getIndex();
				matrix[v.getIndex()][loc] = n.getCapacity();
			}
		}
	}

}
