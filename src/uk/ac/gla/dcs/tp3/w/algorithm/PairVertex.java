/**
 * 
 */
package uk.ac.gla.dcs.tp3.w.algorithm;

import uk.ac.gla.dcs.tp3.w.league.Team;

/**
 * @author gordonreid
 * 
 */
public class PairVertex extends Vertex {

	private Team teamA;
	private Team teamB;

	public PairVertex() {
		this(null, null, -1);
	}

	public PairVertex(Team a, Team b, int n) {
		super(n);
		teamA = a;
		teamB = b;
	}

	public Team getTeamA() {
		return teamA;
	}

	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}

	public Team getTeamB() {
		return teamB;
	}

	public void setTeamB(Team teamB) {
		this.teamB = teamB;
	}

}
