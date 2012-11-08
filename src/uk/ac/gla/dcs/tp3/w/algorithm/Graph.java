package uk.ac.gla.dcs.tp3.w.algorithm;

import uk.ac.gla.dcs.tp3.w.league.League;
import uk.ac.gla.dcs.tp3.w.league.Match;
import uk.ac.gla.dcs.tp3.w.league.Team;


public class Graph {

	private Vertex[] v;
	private int[][] matrix;
	private Vertex source;
	private Vertex sink;

	public Graph(){
		this(null, null);
	}
	
	public Graph(League l, Team t){
		source = new Vertex();
		sink = new Vertex();
		int teamTotal = l.getTeams().length -1;
		int gameTotal = comb(teamTotal,2);
		v = new Vertex[teamTotal + gameTotal + 2];
		v[0] = source;
		v[v.length-1] = sink;
		for (int i=1; i<=gameTotal; i++){
			v[i] = new Vertex();
			v[0].getAdjList().add(new AdjListNode(0,v[i]));
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
	
	private static int fact(int s){
		return (s<2) ? 1 : s*fact(s-1);
	}
	
	private static int comb(int n, int r){
		return (fact(n)/(fact(r)*fact(n-r)));
	}

}
