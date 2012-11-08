package uk.ac.gla.dcs.tp3.w.algorithm;

import uk.ac.gla.dcs.tp3.w.league.League;
import uk.ac.gla.dcs.tp3.w.league.Match;
import uk.ac.gla.dcs.tp3.w.league.Team;

public class Graph {

	private Vertex[] v;
	private int[][] matrix;
	private Vertex source;
	private Vertex sink;

	public Graph() {
		this(null, null);
	}

	public Graph(League l, Team t) {
		if (l == null || t == null)
			return;
		// Create blank vertices for source and sink.
		source = new Vertex();
		sink = new Vertex();
		// Number of team nodes is one less than total number of teams.
		// The team nodes do not include the team being tested for elimination.
		int teamTotal = l.getTeams().length - 1;
		// The r-combination of teamTotal for length 2 is the number of possible
		// combinations for matches between the list of Teams-{t}.
		int gameTotal = comb(teamTotal, 2);
		// Total number of vertices is the number of teams-1 + number of match
		// pairs + source + sink.
		v = new Vertex[teamTotal + gameTotal + 2];
		// Set first vertex to be source, and last vertex to be sink.
		v[0] = source;
		v[v.length - 1] = sink;
		// Create vertex for each match pair and make it adjacent from the
		// source.
		for (int i = 1; i <= gameTotal; i++) {
			v[i] = new Vertex();
			v[0].getAdjList().add(new AdjListNode(0, v[i]));
		}

	}

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

	private static int fact(int s) {
		// For s < 2, the factorial is 1. Otherwise, multiply s by fact(s-1)
		return (s < 2) ? 1 : s * fact(s - 1);
	}

	private static int comb(int n, int r) {
		// r-combination of size n is n!/r!(n-r)!
		return (fact(n) / (fact(r) * fact(n - r)));
	}

}
