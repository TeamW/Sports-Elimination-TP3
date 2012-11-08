package uk.ac.gla.dcs.tp3.w.algorithm;

import uk.ac.gla.dcs.tp3.w.league.League;
import uk.ac.gla.dcs.tp3.w.league.Team;

public class Algorithm {
	
	private Graph g;
	private League l;
	
	public Algorithm() {
		this(null);
	}
	
	public Algorithm(League league) {
		g = null;
		l = league;
	}
	
	public Graph getG() {
		return g;
	}

	public void setG(Graph g) {
		this.g = g;
	}

	public League getL() {
		return l;
	}

	public void setL(League l) {
		this.l = l;
	}

	public boolean isEliminated(Team t) {
		if(t.isEliminated()) {
			return true;
		}
		Graph NFGraph = new Graph();
		return false;
	}

}
